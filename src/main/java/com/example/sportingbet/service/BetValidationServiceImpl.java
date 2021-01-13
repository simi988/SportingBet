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
public class BetValidationServiceImpl implements BetValidationService {
    @Autowired
    BetRepository betRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    PrognosticRepository prognosticRepository;


    @Override
    public ResponseEntity<Object> setEventScore(String location) throws IOException {
        Map<Long, String> longStringMap = readCSVFile(location);
        String message = "";
        for (Map.Entry<Long, String> longStringEntry : longStringMap.entrySet()) {
            try {
                EventDO eventDO = getEvent(longStringEntry.getKey());
                eventDO.setScore(longStringEntry.getValue());
                checkWin(eventDO);
                eventRepository.save(eventDO);

            } catch (EventException eventException) {
                message = " ,but you have some trouble with this:  " + eventException.getMessage();

            }
        }
        return new ResponseEntity<>("Event score is added" + message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> winBet(Long id) throws UserException, EventException {
        Optional<BetDO> betDO = betRepository.findById(id);
        if (betDO.isPresent()) {
            if (betDO.get().isWin() == null) {
                if (!checkBet(betDO.get())) {
                    return new ResponseEntity<>("Your Ticket's is lose", HttpStatus.OK);
                }
            } else {
                return checkValidation(betDO.get(), betDO.get().isWin());
            }
        } else {
            throw new UserException("Bet is not exist", HttpStatus.BAD_REQUEST);
        }
        double winSum = calculateSum(betDO.get());
        BetDO bet = betDO.get();
        bet.setWin(true);
        betRepository.save(bet);
        String message = "Your Ticket's is win! You win " + winSum + " RON";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    private ResponseEntity<Object> checkValidation(BetDO betDO, boolean aBoolean) {
        if (!aBoolean) {
            return new ResponseEntity<>("Your Ticket's is lose", HttpStatus.OK);
        }
        double winSum = calculateSum(betDO);
        String message = "Your Ticket's is win! You win " + winSum + " RON";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    private boolean checkBet(BetDO betDO) throws EventException {
        Map<Long, Long> eventMap = betDO.getEventList();
        for (Map.Entry<Long, Long> entry : eventMap.entrySet()) {
            EventDO eventDO = getEvent(entry.getKey());
            Optional<PrognosticsDO> prognosticsDO = prognosticRepository.findById(entry.getValue());
            if (prognosticsDO.isPresent()) {
                if (!eventDO.getOddWinList().contains(prognosticsDO.get())) {
                    return false;
                }
            } else {
                throw new EventException("Prognostic is not exist", HttpStatus.BAD_REQUEST);
            }
        }
        return true;
    }

    private EventDO getEvent(Long id) throws EventException {
        Optional<EventDO> eventRepositoryById = eventRepository.findById(id);
        if (eventRepositoryById.isPresent()) {
            return eventRepositoryById.get();

        } else {
            throw new EventException("Your Event id not exist " + id, HttpStatus.BAD_REQUEST);
        }
    }

    private void checkWin(EventDO eventDO) throws EventException {
        String score = eventDO.getScore();
        if (score != null) {
            String[] split = score.split("-");
            List<Integer> scoreList = getScoreList(split);
            if (scoreList.get(0) > scoreList.get(1)) {
                List<Odd> oddList = Arrays.asList(Odd.X1, Odd.UNU, Odd.PSF1);
                eventDO.setOddWinList( addPrognostic(eventDO,oddList));


            } else if (scoreList.get(0).equals(scoreList.get(1))) {
                List<Odd> oddList = Arrays.asList(Odd.X2, Odd.X1, Odd.X, Odd.PSFX);
                eventDO.setOddWinList( addPrognostic(eventDO,oddList));


            } else {
                List<Odd> oddList = Arrays.asList(Odd.X2, Odd.DOI, Odd.PSF2);
                eventDO.setOddWinList( addPrognostic(eventDO,oddList));

            }
        } else {
            throw new EventException("Event is not finish", HttpStatus.BAD_REQUEST);
        }
    }

    private  List<PrognosticsDO> addPrognostic(EventDO eventDO,List<Odd> oddList){
        List<PrognosticsDO> prognosticsDOList = new ArrayList<>();
        for (PrognosticsDO prognostic : eventDO.getPrognostics()) {
            if (oddList.contains(prognostic.getOdd())) {
                prognosticsDOList.add(prognostic);
            }
        }
        return prognosticsDOList;

    }

    private List<Integer> getScoreList(String[] split){
        List<Integer> scoreList = new ArrayList<>();
        for (String number : split) {
            int scoreNumber = Integer.parseInt(number);
            scoreList.add(scoreNumber);
        }
        return scoreList;
    }

    private double calculateSum(BetDO betDO) {
        return betDO.getSum() * betDO.getOdd();
    }

    private Map<Long, String> readCSVFile(String location) throws IOException {
        Map<Long, String> records = new HashMap<>();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        File file = new File(Objects.requireNonNull(classloader.getResource(location)).getFile());
        InputStream inputStream = new FileInputStream(file);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Long id = Long.valueOf(values[0]);
                records.put(id, values[1]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return records;
    }
}
