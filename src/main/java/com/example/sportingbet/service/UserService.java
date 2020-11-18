package com.example.sportingbet.service;

import com.example.sportingbet.exception.UserException;
import com.example.sportingbet.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    boolean insertUser(User user) throws UserException;

    List<User> getAllUser();

    Optional<User> getUserById(UUID id);

    boolean deleteUser(UUID id) throws UserException;

    boolean updateUser(UUID id, User newUser) throws UserException;

    double getUserMoneyById(UUID id);

    boolean updateUserMoneyById(UUID id, double money) throws UserException;


}
