package com.example.sportingbet.dao;

import com.example.sportingbet.exception.UserException;
import com.example.sportingbet.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDAO {
    void insert(User user) throws UserException;

    List<User> selectAllUser();

    Optional<User> selectUserById(Long id) throws UserException;

    boolean deleteUserById(Long id) throws UserException;

    boolean updateUserById(Long id, User user) throws UserException;

    double getUserMoneyById(Long id) throws UserException;

    boolean updateUserMoneyById(Long id, double money) throws UserException;


}
