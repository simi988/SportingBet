package com.example.sportingbet.service;

import com.example.sportingbet.exception.UserException;
import com.example.sportingbet.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    boolean insertUser(User user) throws UserException;

    List<User> getAllUser();

    User getUserById(UUID id) throws UserException;

    boolean deleteUser(UUID id) throws UserException;

    boolean updateUser(UUID id, User newUser) throws UserException;

    double getUserMoneyById(UUID id) throws UserException;

    boolean updateUserMoneyById(UUID id, double money) throws UserException;


}
