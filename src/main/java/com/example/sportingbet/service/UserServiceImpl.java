package com.example.sportingbet.service;

import com.example.sportingbet.dao.UserDAO;
import com.example.sportingbet.exception.UserException;
import com.example.sportingbet.model.LoginUser;
import com.example.sportingbet.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
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

    public User getUserById(UUID id) throws UserException {
        Optional<User> user = userDao.selectUserById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UserException("User don't exist", HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
    }

    public boolean deleteUser(UUID id) throws UserException {
        return userDao.deleteUserById(id);
    }

    public boolean updateUser(UUID id, User newUser) throws UserException {
        return userDao.updateUserById(id, newUser);
    }

    public double getUserMoneyById(UUID id) throws UserException {
        return userDao.getUserMoneyById(id);
    }

    public boolean updateUserMoneyById(UUID id, double money) throws UserException {
        return userDao.updateUserMoneyById(id, money);
    }

    public User login(String username, String password) throws UserException {
        List<User> users = userDao.selectAllUser();
        for (User user : users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                if (LoginUser.getUser() != null) {
                    throw new UserException("Session is full", HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
                }
                LoginUser.setUser(user);
                return user;
            }

        }
        throw new UserException("User or Password incorrect", HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
    }

    public User logout() throws UserException {
        User user = LoginUser.getUser();
        if (user == null) {
            throw new UserException("Login first", HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
        }
        LoginUser.setUser(null);

        return user;
    }

}
