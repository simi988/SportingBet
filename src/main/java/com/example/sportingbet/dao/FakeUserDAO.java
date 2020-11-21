package com.example.sportingbet.dao;

import com.example.sportingbet.exception.UserException;
import com.example.sportingbet.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository("fakeDao")
public class FakeUserDAO implements UserDAO {
    public static final String ALPHABET = "[^A-Za-z0-9]";
    private final List<User> dataBase = new ArrayList<>();

    @Override
    public boolean insertUser(UUID id, User user) throws UserException {
        validateUser(user);
        return dataBase.add(new User(id, user.getName(), user.getMoney(), user.getUserName(), user.getPassword()));
    }

    @Override
    public List<User> selectAllUser() {
        return dataBase;
    }

    @Override
    public Optional<User> selectUserById(UUID id) {
        return dataBase.stream().filter(client -> client.getId().equals(id))
                .findFirst();

    }

    @Override
    public boolean deleteUserById(UUID id) throws UserException {
        Optional<User> deleteUser = selectUserById(id);
        if (deleteUser.isEmpty()) {
            throw new UserException("User don't exist", HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
        }
        User user = deleteUser.get();
        return dataBase.remove(user);
    }

    @Override
    public boolean updateUserById(UUID id, User updateUser) {
        Optional<Boolean> aBoolean = selectUserById(id)
                .map(user -> {
                    int indexOfUserToUpdate = dataBase.indexOf(user);
                    if (indexOfUserToUpdate >= 0) {
                        dataBase.set(indexOfUserToUpdate, new User(id, updateUser.getName(), updateUser.getMoney(), updateUser.getUserName(), updateUser.getPassword()));
                        return true;
                    }
                    return false;
                });
        return aBoolean.isPresent();
    }


    @Override
    public double getUserMoneyById(UUID id) throws UserException {
        Optional<User> optionalUser = selectUserById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getMoney();
        }
        throw new UserException("The user is not exist", HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
    }

    @Override
    public boolean updateUserMoneyById(UUID id, double money) {
        Optional<User> optionalUser = selectUserById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setMoney(money);
            return true;
        }
        return false;
    }

    private void validateUser(User user) throws UserException {
        Pattern pattern = Pattern.compile(ALPHABET);
        Matcher match = pattern.matcher(user.getName());

        if (match.find()) {
            throw new UserException("The name must not contain any special characters", HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
        }

        for (User userName : dataBase) {
            if (user.getUserName().equals(userName.getUserName())) {
                throw new UserException("Username is already exist", HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
            }
        }
    }


}
