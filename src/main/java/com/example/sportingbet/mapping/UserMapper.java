package com.example.sportingbet.mapping;

import com.example.sportingbet.entity.UserDO;
import com.example.sportingbet.model.User;

public class UserMapper {

    public User mapToDto(UserDO userDO) {
        User user=new User();
        user.setId(userDO.getId());
        user.setMoney(userDO.getMoney());
        user.setName(userDO.getName());
        user.setUsername(userDO.getUsername());
        user.setPassword(userDO.getPassword());
        return user;
    }

    public UserDO mapToDO(User user) {
        UserDO userDO = new UserDO();
        userDO.setMoney(user.getMoney());
        userDO.setName(user.getName());
        userDO.setUsername(user.getUsername());
        userDO.setPassword(user.getPassword());
        return userDO;
    }
}
