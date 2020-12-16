package com.example.sportingbet.service;

import com.example.sportingbet.exception.UserException;
import com.example.sportingbet.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    boolean insertUser(User user) throws UserException;

    List<User> getAllUser();

    User getUserById(Long id) throws UserException;

    boolean deleteUser(Long id) throws UserException;

    boolean updateUser(Long id, User newUser) throws UserException;

    double getUserMoneyById(Long id) throws UserException;

    boolean updateUserMoneyById(Long id, double money) throws UserException;

    User login(String username, String password) throws UserException;

    User logout() throws UserException;
}
