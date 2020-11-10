package com.example.sportingbet.service;

import com.example.sportingbet.dao.UserDAO;
import com.example.sportingbet.exception.DuplicateUsernameException;
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

    public void insertUser(User user) throws DuplicateUsernameException {
        userDao.insertUser(user);
    }

    public List<User> getAllUser() {
        return userDao.selectAllUser();
    }

    public Optional<User> getUserById(UUID id) {
        return userDao.selectUserById(id);
    }

    public void deleteUser(UUID id) {
        userDao.deleteUserById(id);
    }

    public void updateUser(UUID id, User newUser) {
        userDao.updateUserById(id, newUser);
    }

    public double getUserMoneyById(UUID id) {
        return userDao.getUserMoneyById(id);
    }

    public void updateUserMoneyById(UUID id, double money) {
        userDao.updateUserMoneyById(id, money);
    }

}
