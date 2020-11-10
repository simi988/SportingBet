package com.example.sportingbet.dao;

import com.example.sportingbet.model.DublicateUsername;
import com.example.sportingbet.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeUserDAO implements UserDAO {
    private static List<User> dataBase = new ArrayList<>();

    @Override
    public int insertUser(UUID id, User user) throws Exception {
        validateUsername(user);
        dataBase.add(new User(id, user.getName(), user.getMoney(), user.getUserName(), user.getPassword()));
        return 1;
    }

    @Override
    public List<User> selectAllUser() {
        return dataBase;
    }

    @Override
    public Optional<User> selectUserById(UUID id) {
        return dataBase.stream().filter(client -> client.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteUserById(UUID id) {
        Optional<User> deleteUser = selectUserById(id);
        if (deleteUser.isEmpty()) {
            return 0;
        }
        dataBase.remove(deleteUser.get());
        return 1;
    }

    @Override
    public int updateUserById(UUID id, User updateUser) {
        return selectUserById(id)
                .map(user -> {
                    int indexOfUserToUpdate = dataBase.indexOf(user);
                    if (indexOfUserToUpdate >= 0) {
                        dataBase.set(indexOfUserToUpdate, new User(id, updateUser.getName(), updateUser.getMoney(), updateUser.getUserName(), updateUser.getPassword()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }


    @Override
    public double getUserMoneyById(UUID id) {
        Optional<User> optionalUser = selectUserById(id);
        User user = optionalUser.get();
        return user.getMoney();
    }

    @Override
    public User updateUserMoneyById(UUID id, double money) {
        Optional<User> optionalUser = selectUserById(id);
        User user = optionalUser.get();
        return user.setMoney(money);
    }

    private boolean validateUsername(User user) throws Exception {
        for (User userName : dataBase) {
            if (user.getUserName().equals(userName.getUserName())) {
                throw new DublicateUsername("Username is already exist");
            }
        }
        return true;
    }

}
