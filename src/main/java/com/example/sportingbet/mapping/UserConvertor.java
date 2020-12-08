package com.example.sportingbet.mapping;

import com.example.sportingbet.entity.UserDO;
import com.example.sportingbet.model.User;

public class UserConvertor {

    public User convertToUser(UserDO userDO) {
        return new User(userDO.getId(), userDO.getName(), userDO.getMoney(), userDO.getUsername(), userDO.getPassword());
    }

    public UserDO convertToUserDo(User user) {
        UserDO userDO = new UserDO();
        userDO.setMoney(user.getMoney());
        userDO.setName(user.getName());
        userDO.setUsername(user.getUserName());
        userDO.setPassword(user.getPassword());
        return userDO;
    }
}
