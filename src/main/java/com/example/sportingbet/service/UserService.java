package com.example.sportingbet.service;

import com.example.sportingbet.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    int insertUser(User user);

    List<User> getAllUser();

    Optional<User> getUserById(UUID id);

    int deleteUser(UUID id);

    int updateUser(UUID id, User newUser);

    double getUserMoneyById(UUID id);

    User updateUserMoneyById(UUID id, double money);


}
