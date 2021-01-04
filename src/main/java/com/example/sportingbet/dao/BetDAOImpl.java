package com.example.sportingbet.dao;

import com.example.sportingbet.entity.BetDO;
import com.example.sportingbet.entity.UserDO;
import com.example.sportingbet.exception.BetException;
import com.example.sportingbet.mapping.BetMapper;
import com.example.sportingbet.model.Bet;
import com.example.sportingbet.repository.BetRepository;
import com.example.sportingbet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("betdao")
public class BetDAOImpl implements BetDAO {
    @Autowired
    BetRepository betRepository;

    BetMapper betMapper = new BetMapper();

    @Autowired
    UserRepository userRepository;

    @Override
    public void addBet(BetDO betDO) {
        UserDO userDO = betDO.getUser();
        BetDO save = betRepository.save(betDO);
        List<BetDO> userDOList=new ArrayList<>();
        userDOList.add(save);
        userDO.setBetDO(userDOList);
        userRepository.save(userDO);
    }

    @Override
    public List<Bet> getAllBets() {
        List<Bet> betList = new ArrayList<>();
        for (BetDO betDO : betRepository.findAll()) {
            betList.add(betMapper.mapToDto(betDO));
        }
        return betList;
    }

    @Override
    public List<Bet> getAllBetsFromCurrentUser(UserDO user)  {
        List<Bet> betList = new ArrayList<>();
        for (BetDO betDO : user.getBetDO()) {
           betList.add(betMapper.mapToDto(betDO));
        }
        return betList;
    }

    @Override
    public Bet findBetById(Long id) throws BetException {
        Optional<BetDO> byId = betRepository.findById(id);
        if (byId.isPresent()) {
            return betMapper.mapToDto(byId.get());
        } else {
            throw new BetException("Invalid bet", HttpStatus.BAD_REQUEST);
        }
    }
}
