package com.example.sportingbet.dao;

import com.example.sportingbet.exception.UserException;
import com.example.sportingbet.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDAO {
    void insertUser(UUID id, User user) throws UserException;

    default void insertUser(User user) throws UserException {
        UUID id = UUID.randomUUID();
        insertUser(id, user);
    }

    List<User> selectAllUser();

    Optional<User> selectUserById(UUID id);

    void deleteUserById(UUID id);

    void updateUserById(UUID id, User user);

    double getUserMoneyById(UUID id);

    void updateUserMoneyById(UUID id, double money);
}
