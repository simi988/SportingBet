package com.example.sportingbet.dao;

import com.example.sportingbet.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDAO {
    int insertUser(UUID id, User user) throws Exception;

    default int insertUser(User user) throws Exception {
        UUID id = UUID.randomUUID();
        return insertUser(id, user);
    }

    List<User> selectAllUser();

    Optional<User> selectUserById(UUID id);

    int deleteUserById(UUID id);

    int updateUserById(UUID id, User user);

    double getUserMoneyById(UUID id);

    User updateUserMoneyById(UUID id, double money);
}
