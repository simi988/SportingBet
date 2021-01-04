package com.example.sportingbet.service;

import com.example.sportingbet.dao.BetDAO;
import com.example.sportingbet.entity.BetDO;
import com.example.sportingbet.entity.PrognosticsDO;
import com.example.sportingbet.entity.UserDO;
import com.example.sportingbet.exception.BetException;
import com.example.sportingbet.exception.UserException;
import com.example.sportingbet.mapping.BetMapper;
import com.example.sportingbet.mapping.EventMapper;
import com.example.sportingbet.mapping.PrognosticsMapper;
import com.example.sportingbet.mapping.UserMapper;
import com.example.sportingbet.model.Bet;
import com.example.sportingbet.model.LoginUser;
import com.example.sportingbet.model.Prognostics;
import com.example.sportingbet.model.User;
import com.example.sportingbet.repository.EventRepository;
import com.example.sportingbet.repository.PrognosticRepository;
import com.example.sportingbet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BetServiceImpl implements BetService {
    BetDAO betDAO;
    @Autowired
    PrognosticRepository prognosticRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    private LoginUser instance;
    @Autowired
    UserRepository userRepository;

    PrognosticsMapper prognosticsMapper = new PrognosticsMapper();
    EventMapper eventMapper = new EventMapper();
    BetMapper betMapper = new BetMapper();

    @Autowired
    public BetServiceImpl(@Qualifier("betdao") BetDAO betDAO) {
        this.betDAO = betDAO;
    }

    @Override
    public void addBet(Bet bet) throws BetException {
        try {
            UserDO userDO = checkIfUserIsLogin();
            bet.setOdd(calculateOdd(bet.getEventList()));
            userDO.setMoney(checkMoneyFromUser(userDO, bet));
            BetDO betDO = betMapper.mapToDO(bet);
            betDO.setUser(userDO);
            betDAO.addBet(betDO);
        } catch (BetException | UserException exception) {
            throw new BetException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public List<Bet> getAllBets() {
        return betDAO.getAllBets();
    }

    @Override
    public List<Bet> getAllBetsFromCurrentUser() throws BetException, UserException {
        UserDO userDO = checkIfUserIsLogin();
        return betDAO.getAllBetsFromCurrentUser(userDO);
    }

    @Override
    public Bet findBetById(Long id) throws BetException {
        return betDAO.findBetById(id);
    }


    private double calculateOdd(Map<Long, Long> eventList) throws BetException {
        double odd = 0;

        for (Long prognosticId : eventList.values()) {
            Prognostics prognosticById = findPrognosticById(prognosticId);
            if (odd == 0) {
                odd += prognosticById.getValue();
            } else {
                odd *= prognosticById.getValue();
            }
        }
        return odd;
    }

    private Prognostics findPrognosticById(Long id) throws BetException {
        Optional<PrognosticsDO> byId = prognosticRepository.findById(id);
        if (byId.isPresent()) {
            return prognosticsMapper.mapToDto(byId.get());
        } else {
            throw new BetException("Prognostic not exist", HttpStatus.BAD_REQUEST);
        }
    }

    private UserDO checkIfUserIsLogin() throws BetException, UserException {
        UserMapper userMapper = new UserMapper();
        User user = instance.getUser();
        if (user != null) {
            UserDO userDO = userMapper.mapToDO(user);
            Optional<UserDO> byName = userRepository.findByName(userDO.getUsername());
            if (byName.isPresent()) {
                return byName.get();
            } else {
                throw new UserException("User not exist", HttpStatus.BAD_REQUEST);
            }

        } else {
            throw new BetException("You are not login", HttpStatus.BAD_REQUEST);
        }
    }

    private double checkMoneyFromUser(UserDO userDO, Bet bet) throws UserException {
        double money = userDO.getMoney() - bet.getSum();
        if (money < 0) {
            throw new UserException("Insufficient money", HttpStatus.BAD_REQUEST);
        }
        return money;
    }
}
