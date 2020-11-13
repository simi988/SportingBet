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
    private final List<User> dataBase = new ArrayList<>();

    @Override
    public void insertUser(UUID id, User user) throws UserException {
        validateUser(user);
        dataBase.add(new User(id, user.getName(), user.getMoney(), user.getUserName(), user.getPassword()));
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
    public void deleteUserById(UUID id) {
        Optional<User> deleteUser = selectUserById(id);
        if (deleteUser.isEmpty()) {
            return;
        }
        dataBase.remove(deleteUser.get());
    }

    @Override
    public void updateUserById(UUID id, User updateUser) {
        selectUserById(id)
                .map(user -> {
                    int indexOfUserToUpdate = dataBase.indexOf(user);
                    if (indexOfUserToUpdate >= 0) {
                        dataBase.set(indexOfUserToUpdate, new User(id, updateUser.getName(), updateUser.getMoney(), updateUser.getUserName(), updateUser.getPassword()));
                        return 1;
                    }
                    return 0;
                });
    }


    @Override
    public double getUserMoneyById(UUID id) {
        Optional<User> optionalUser = selectUserById(id);
        User user = optionalUser.get();
        return user.getMoney();
    }

    @Override
    public void updateUserMoneyById(UUID id, double money) {
        Optional<User> optionalUser = selectUserById(id);
        User user = optionalUser.get();
        user.setMoney(money);
    }

    private void validateUser(User user) throws UserException {
        Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
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
