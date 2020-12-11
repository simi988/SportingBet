package com.example.sportingbet.dao;

import com.example.sportingbet.entity.UserDO;
import com.example.sportingbet.exception.UserException;
import com.example.sportingbet.mapping.UserMapper;
import com.example.sportingbet.model.User;
import com.example.sportingbet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository("userdao")
public class UserDAOImpl implements UserDAO {
    @Autowired
    private UserRepository userRepository;

    private final UserMapper userMapper = new UserMapper();
    public static final String ALPHABET = "[^A-Za-z0-9]";

    @Override
    public void insert(User user) throws UserException {
        validateUser(user);
        UserDO userDO = userMapper.mapToDO(user);
        userRepository.save(userDO);
    }

    @Override
    public List<User> selectAllUser() {
        List<User> users = new ArrayList<>();
        Iterable<UserDO> all = userRepository.findAll();
        for (UserDO userDO : all) {
            User user = userMapper.mapToDto(userDO);
            users.add(user);
        }
        return users;
    }

    @Override
    public Optional<User> selectUserById(Long id) throws UserException {
        Optional<UserDO> optionalUserDO = userRepository.findById(id);
        if (optionalUserDO.isPresent()) {
            UserDO userDO = optionalUserDO.get();
            User user = userMapper.mapToDto(userDO);
            return Optional.ofNullable(user);
        }
        throw new UserException("User don't exist", HttpStatus.BAD_REQUEST);
    }

    @Override
    public boolean deleteUserById(Long id) throws UserException {
        Optional<UserDO> userDO = userRepository.findById(id);
        if (userDO.isEmpty()) {
            throw new UserException("User don't exist", HttpStatus.BAD_REQUEST);
        }
        userRepository.delete(userDO.get());
        return true;
    }

    @Override
    public boolean updateUserById(Long id, User user) throws UserException {
        Optional<UserDO> byId = userRepository.findById(id);

        if (byId.isPresent()) {
            UserDO userDO = byId.get();
            userDO.setName(user.getName());
            userDO.setMoney(user.getMoney());
            userDO.setPassword(user.getPassword());
            userRepository.save(userDO);
            return true;
        }
        throw new UserException("User don't exist", HttpStatus.BAD_REQUEST);
    }

    @Override
    public double getUserMoneyById(Long id) throws UserException {
        Optional<UserDO> userDOOptional = userRepository.findById(id);
        if (userDOOptional.isPresent()) {
            UserDO userDO = userDOOptional.get();
            return userDO.getMoney();
        }
        throw new UserException("The user is not exist", HttpStatus.BAD_REQUEST);
    }

    @Override
    public boolean updateUserMoneyById(Long id, double money) throws UserException {
        Optional<UserDO> userDOOptional = userRepository.findById(id);
        if (userDOOptional.isPresent()) {
            UserDO userDO = userDOOptional.get();
            userDO.setMoney(money);
            userRepository.save(userDO);
            return true;
        }
        throw new UserException("User don't exist", HttpStatus.BAD_REQUEST);
    }

    private void validateUser(User user) throws UserException {
        Pattern pattern = Pattern.compile(ALPHABET);
        Matcher match = pattern.matcher(user.getUsername());
        if (match.find()) {
            throw new UserException("The name must not contain any special characters", HttpStatus.BAD_REQUEST);
        }
    }
}
