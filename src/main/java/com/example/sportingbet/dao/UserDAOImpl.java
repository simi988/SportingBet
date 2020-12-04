package com.example.sportingbet.dao;

import com.example.sportingbet.entity.UserDO;
import com.example.sportingbet.exception.UserException;
import com.example.sportingbet.mapping.UserConvertor;
import com.example.sportingbet.model.User;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDAOImpl implements UserDAO{
  private  UserRepository userRepository=context.getBean(UserRepository.class);
private UserConvertor userConvertor;
    public static final String ALPHABET = "[^A-Za-z0-9]";
    @Override
    public boolean insertUser(Long id, User user) throws UserException {
        validateUser(user);
        UserDO userDO = userConvertor.convertToUserDo(user);
        userRepository.save(userDO);

    }

    @Override
    public List<User> selectAllUser() {
        return null;
    }

    @Override
    public Optional<User> selectUserById(UUID id) throws UserException {
        return Optional.empty();
    }

    @Override
    public boolean deleteUserById(UUID id) throws UserException {
        return false;
    }

    @Override
    public boolean updateUserById(UUID id, User user) throws UserException {
        return false;
    }

    @Override
    public double getUserMoneyById(UUID id) throws UserException {
        return 0;
    }

    @Override
    public boolean updateUserMoneyById(UUID id, double money) throws UserException {
        return false;
    }
    private void validateUser(User user) throws UserException {
        Pattern pattern = Pattern.compile(ALPHABET);
        Matcher match = pattern.matcher(user.getName());

        if (match.find()) {
            throw new UserException("The name must not contain any special characters", HttpStatus.BAD_REQUEST);
        }

        for (User userName : dataBase) {
            if (user.getUserName().equals(userName.getUserName())) {
                throw new UserException("Username is already exist", HttpStatus.BAD_REQUEST);
            }
        }
    }
}
