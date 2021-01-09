package com.example.sportingbet.service;

import com.example.sportingbet.entity.BetDO;
import com.example.sportingbet.entity.EventDO;
import com.example.sportingbet.entity.PrognosticsDO;
import com.example.sportingbet.exception.EventException;
import com.example.sportingbet.exception.UserException;
import com.example.sportingbet.model.Odd;
import com.example.sportingbet.repository.BetRepository;
import com.example.sportingbet.repository.EventRepository;
import com.example.sportingbet.repository.PrognosticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Autowired
    BetRepository betRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    PrognosticRepository prognosticRepository;

    @Override
    public void setEventScore() throws EventException, IOException {
        List<List<String>> lists = readCSVFile();
        for (List<String> list : lists) {
            String eventId = list.get(0);
            Long id = Long.valueOf(eventId);
            Optional<EventDO> byId = eventRepository.findById(id);
            if (byId.isPresent()) {
                EventDO eventDO = byId.get();
                String eventScore = list.get(1);
                eventDO.setScore(eventScore);
                eventRepository.save(eventDO);
            } else {
                throw new EventException("Event not exist", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @Override
    public ResponseEntity<Object> winBet(Long id) throws UserException, EventException {
        Optional<BetDO> betDO = betRepository.findById(id);
        if (betDO.isPresent()) {
            Map<Long, Long> eventMap = betDO.get().getEventList();
            for (Map.Entry<Long, Long> entry : eventMap.entrySet()) {

                Optional<EventDO> eventDO = eventRepository.findById(entry.getKey());
                if (eventDO.isPresent()) {
                    Optional<PrognosticsDO> prognosticsDO = prognosticRepository.findById(entry.getValue());
                    if (prognosticsDO.isPresent()&&!checkWin(eventDO.get()).contains(prognosticsDO.get().getOdd())) {
                        return new ResponseEntity<>("Your Ticket's is lose", HttpStatus.OK);
                    }
                }
            }

            double winSum = calculateSum(betDO.get());
            String message="Your Ticket's is win! You win "+winSum+" RON";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            throw new UserException("Bet is not exist", HttpStatus.BAD_REQUEST);
        }
    }


    private List<Odd> checkWin(EventDO eventDO) throws EventException {
        String score = eventDO.getScore();
        if (score != null) {
            String[] split = score.split("-");
            List<Integer> scoreList = new ArrayList<>();
            for (String number : split) {
                int scoreNumber = Integer.parseInt(number);
                scoreList.add(scoreNumber);
            }
            if (scoreList.get(0) > scoreList.get(1)) {

                return Arrays.asList(Odd.X1, Odd.UNU, Odd.PSF1);

            } else if (scoreList.get(0).equals(scoreList.get(1))) {
                return Arrays.asList(Odd.X2, Odd.X1, Odd.X, Odd.PSFX);

            } else {
                return Arrays.asList(Odd.X2, Odd.DOI, Odd.PSF2);
            }
        } else {
            throw new EventException("Event is not finish", HttpStatus.BAD_REQUEST);
        }
    }
    private double calculateSum(BetDO betDO){
        return betDO.getSum()*betDO.getOdd();
    }

    private List<List<String>> readCSVFile() throws IOException {
        List<List<String>> records = new ArrayList<>();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        File file = new File(Objects.requireNonNull(classloader.getResource("Score.txt")).getFile());
        InputStream inputStream = new FileInputStream(file);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return records;
    }
}
