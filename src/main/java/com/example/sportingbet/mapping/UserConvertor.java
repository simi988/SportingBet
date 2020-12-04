package com.example.sportingbet.mapping;

import com.example.sportingbet.entity.UserDO;
import com.example.sportingbet.model.User;

public class UserConvertor {
    public User convertToUser(UserDO userDO){
        return new User(userDO.getId(),userDO.getName(),userDO.getMoney(),userDO.getUsername(),userDO.getPassword());
    }
    public UserDO convertToUserDo(User user){
        return new UserDO(user.getId(),user.getName(), user.getMoney(), user.getUserName(), user.getPassword());
    }
}
