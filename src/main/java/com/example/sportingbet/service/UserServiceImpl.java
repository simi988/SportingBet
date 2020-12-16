package com.example.sportingbet.service;

import com.example.sportingbet.dao.UserDAO;
import com.example.sportingbet.exception.UserException;
import com.example.sportingbet.model.LoginUser;
import com.example.sportingbet.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private LoginUser instance;

    private final UserDAO userDao;

    @Autowired
    public UserServiceImpl(@Qualifier("userdao") UserDAO userDao) {
        this.userDao = userDao;
    }

    public boolean insertUser(User user) throws UserException {
        try {
        userDao.insert(user);
        return true;
        }catch (DataIntegrityViolationException exception) {
            throw new UserException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public List<User> getAllUser() {
        return userDao.selectAllUser();
    }

    public User getUserById(Long id) throws UserException {
        return userDao.selectUserById(id).orElseThrow(() -> new UserException("User don't exist", HttpStatus.BAD_REQUEST));
    }

    public boolean deleteUser(Long id) throws UserException {
        try {
            return userDao.deleteUserById(id);
        }catch (DataIntegrityViolationException exception) {
            throw new UserException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public boolean updateUser(Long id, User newUser) throws UserException {
        try{
            return userDao.updateUserById(id, newUser);
        }catch (DataIntegrityViolationException exception) {
            throw new UserException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    public double getUserMoneyById(Long id) throws UserException {
        return userDao.getUserMoneyById(id);
    }

    public boolean updateUserMoneyById(Long id, double money) throws UserException {
        try{
        return userDao.updateUserMoneyById(id, money);
    }catch (DataIntegrityViolationException exception) {
        throw new UserException(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    }

    public User login(String username, String password) throws UserException {

        if (instance.getUser() != null) {
            throw new UserException("Session is full", HttpStatus.BAD_REQUEST);
        }
        List<User> users = userDao.selectAllUser();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                instance.setUser(user);
                return user;
            }
        }
        throw new UserException("User or Password incorrect", HttpStatus.BAD_REQUEST);
    }

    public User logout() throws UserException {

        User user = instance.getUser();
        if (user == null) {
            throw new UserException("Login first", HttpStatus.BAD_REQUEST);
        }
        instance.setUser(null);
        return user;
    }

}
