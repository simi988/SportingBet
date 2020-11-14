package com.example.sportingbet.dao;

import com.example.sportingbet.exception.UserException;
import com.example.sportingbet.model.ApiResponse;
import com.example.sportingbet.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDAO {
    ApiResponse insertUser(UUID id, User user) throws UserException;

    default ApiResponse insertUser(User user) throws UserException {
        UUID id = UUID.randomUUID();
        return insertUser(id, user);
    }

    List<User> selectAllUser();

    Optional<User> selectUserById(UUID id);

    ApiResponse deleteUserById(UUID id) throws UserException;

    ApiResponse updateUserById(UUID id, User user) throws UserException;

    double getUserMoneyById(UUID id);

    ApiResponse updateUserMoneyById(UUID id, double money) throws UserException;
}
