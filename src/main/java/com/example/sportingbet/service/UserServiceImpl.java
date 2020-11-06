package com.example.sportingbet.service;

import com.example.sportingbet.dao.UserDAO;
import com.example.sportingbet.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDao;

    @Autowired
    public UserServiceImpl(@Qualifier("fakeDao") UserDAO userDao) {
        this.userDao = userDao;
    }

    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    public List<User> getAllUser() {
        return userDao.selectAllUser();
    }

    public Optional<User> getUserById(UUID id) {
        return userDao.selectUserById(id);
    }

    public int deleteUser(UUID id) {
        return userDao.deleteUserById(id);
    }

    public int updateUser(UUID id, User newUser) {
        return userDao.updateUserById(id, newUser);
    }

    public double getUserMoneyById(UUID id) {
        return userDao.getUserMoneyById(id);
    }

    public User updateUserMoneyById(UUID id, double money) {
        return userDao.updateUserMoneyById(id, money);
    }

}
