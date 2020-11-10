package com.example.sportingbet.dao;

import com.example.sportingbet.exception.DuplicateUsernameException;
import com.example.sportingbet.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeUserDAO implements UserDAO {
    private final List<User> dataBase = new ArrayList<>();

    @Override
    public void insertUser(UUID id, User user) throws DuplicateUsernameException {
        validateUsername(user);
        dataBase.add(new User(id, user.getName(), user.getMoney(), user.getUserName(), user.getPassword()));
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
    public void deleteUserById(UUID id) {
        Optional<User> deleteUser = selectUserById(id);
        if (deleteUser.isEmpty()) {
            return;
        }
        dataBase.remove(deleteUser.get());
    }

    @Override
    public void updateUserById(UUID id, User updateUser) {
        selectUserById(id)
                .map(user -> {
                    int indexOfUserToUpdate = dataBase.indexOf(user);
                    if (indexOfUserToUpdate >= 0) {
                        dataBase.set(indexOfUserToUpdate, new User(id, updateUser.getName(), updateUser.getMoney(), updateUser.getUserName(), updateUser.getPassword()));
                        return 1;
                    }
                    return 0;
                });
    }


    @Override
    public double getUserMoneyById(UUID id) {
        Optional<User> optionalUser = selectUserById(id);
        User user = optionalUser.get();
        return user.getMoney();
    }

    @Override
    public void updateUserMoneyById(UUID id, double money) {
        Optional<User> optionalUser = selectUserById(id);
        User user = optionalUser.get();
        user.setMoney(money);
    }

    private void validateUsername(User user) throws DuplicateUsernameException {
        for (User userName : dataBase) {
            if (user.getUserName().equals(userName.getUserName())) {
                throw new DuplicateUsernameException("Username is already exist");
            }
        }

    }

}
