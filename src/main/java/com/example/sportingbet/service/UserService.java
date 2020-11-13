package com.example.sportingbet.service;

import com.example.sportingbet.exception.UserException;
import com.example.sportingbet.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    void insertUser(User user) throws UserException;

    List<User> getAllUser();

    Optional<User> getUserById(UUID id);

    void deleteUser(UUID id) throws UserException;

    void updateUser(UUID id, User newUser) throws UserException;

    double getUserMoneyById(UUID id);

    void updateUserMoneyById(UUID id, double money) throws UserException;


}
