package com.example.sportingbet.dao;

import com.example.sportingbet.exception.UserException;
import com.example.sportingbet.model.ApiResponse;
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
    public ApiResponse insertUser(UUID id, User user) throws UserException {
        validateUser(user);
        int size = dataBase.size();
        dataBase.add(new User(id, user.getName(), user.getMoney(), user.getUserName(), user.getPassword()));
        return validateOperation(user, "added", size + 1 == dataBase.size());
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
    public ApiResponse deleteUserById(UUID id) throws UserException {
        Optional<User> deleteUser = selectUserById(id);
        if (deleteUser.isEmpty()) {
            return null;
        }
        int size = dataBase.size();
        User user = deleteUser.get();
        dataBase.remove(user);
        return validateOperation(user, "deleted", size - 1 == dataBase.size());
    }

    @Override
    public ApiResponse updateUserById(UUID id, User updateUser) throws UserException {
        int size = dataBase.size();
        selectUserById(id)
                .map(user -> {
                    int indexOfUserToUpdate = dataBase.indexOf(user);
                    if (indexOfUserToUpdate >= 0) {
                        dataBase.set(indexOfUserToUpdate, new User(id, updateUser.getName(), updateUser.getMoney(), updateUser.getUserName(), updateUser.getPassword()));
                        return 1;
                    }
                    return 0;
                });
        int sizeAfterUpdate = dataBase.size();
        return size == sizeAfterUpdate ? validateOperation(updateUser, "update", true) :
                validateOperation(updateUser, "update", false);
    }


    @Override
    public double getUserMoneyById(UUID id) {
        Optional<User> optionalUser = selectUserById(id);
        User user = optionalUser.get();
        return user.getMoney();
    }

    @Override
    public ApiResponse updateUserMoneyById(UUID id, double money) throws UserException {
        int size = dataBase.size();
        Optional<User> optionalUser = selectUserById(id);
        User user = optionalUser.get();
        user.setMoney(money);
        int sizeAfterUpdate = dataBase.size();
        return size == sizeAfterUpdate ? validateOperation(user, "update", true) :
                validateOperation(user, "update", false);
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

    private ApiResponse validateOperation(User user, String name, boolean b) throws UserException {
        if (b) {
            String message = "The user " + user.getUserName() + " has " + name;
            return new ApiResponse(message, HttpStatus.OK, ZonedDateTime.now(ZoneId.of("Z")));
        } else {
            String message = "The user " + user.getUserName() + " can't " + name;
            throw new UserException(message, HttpStatus.OK, ZonedDateTime.now(ZoneId.of("Z")));
        }
    }

}
