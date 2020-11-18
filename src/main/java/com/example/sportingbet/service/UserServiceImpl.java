package com.example.sportingbet.service;

import com.example.sportingbet.dao.UserDAO;
import com.example.sportingbet.exception.UserException;
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

    public boolean insertUser(User user) throws UserException {
        return userDao.insertUser(user);
    }

    public List<User> getAllUser() {
        return userDao.selectAllUser();
    }

    public Optional<User> getUserById(UUID id) {
        return userDao.selectUserById(id);
    }

    public boolean deleteUser(UUID id) throws UserException {
        return userDao.deleteUserById(id);
    }

    public boolean updateUser(UUID id, User newUser) throws UserException {
        return userDao.updateUserById(id, newUser);
    }

    public double getUserMoneyById(UUID id) {
        return userDao.getUserMoneyById(id);
    }

    public boolean updateUserMoneyById(UUID id, double money) throws UserException {
        return userDao.updateUserMoneyById(id, money);
    }

}
