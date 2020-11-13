package com.example.sportingbet.api;

import com.example.sportingbet.exception.UserException;
import com.example.sportingbet.model.User;
import com.example.sportingbet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/user")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void insertUser(@Valid @NonNull @RequestBody User user) throws UserException {
        userService.insertUser(user);
    }

    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping(path = "getuser/{id}")
    public User getUserById(@PathVariable("id") UUID id) {
        return userService.getUserById(id)
                .orElse(null);
    }

    @GetMapping(path = "getusermoney/{id}")
    public double getUserMoneyById(@PathVariable("id") UUID id) {
        return userService.getUserMoneyById(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUserById(@PathVariable("id") UUID id) {
        userService.deleteUser(id);
    }

    @PutMapping(path = "{id}")
    public void updateUser(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody User userToUpdate) {
        userService.updateUser(id, userToUpdate);
    }

    @PutMapping(path = "updateusermoney/{id}/{money}")
    public void updateUserMoneyById(@PathVariable("id") UUID id, @PathVariable("money") double money) {
        userService.updateUserMoneyById(id, money);
    }

}
