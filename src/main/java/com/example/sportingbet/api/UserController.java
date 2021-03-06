package com.example.sportingbet.api;

import com.example.sportingbet.exception.UserException;
import com.example.sportingbet.model.ApiResponse;
import com.example.sportingbet.model.User;
import com.example.sportingbet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/v1/user")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> insertUser(@Valid @NonNull @RequestBody User user) throws UserException {
        boolean response = userService.insertUser(user);
        return giveResponse(user, "added", response);
    }

    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping(path = "getuser/{id}")
    public User getUserById(@PathVariable("id") Long id) throws UserException {
        return userService.getUserById(id);
    }

    @GetMapping(path = "getusermoney/{id}")
    public double getUserMoneyById(@PathVariable("id") Long id) throws UserException {
        return userService.getUserMoneyById(id);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable("id") Long id) throws UserException {
        User user = userService.getUserById(id);
        boolean response = userService.deleteUser(id);
        return giveResponse(user, "deleted", response);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") Long id, @Valid @NonNull @RequestBody User userToUpdate) throws UserException {
        boolean response = userService.updateUser(id, userToUpdate);
        return giveResponse(userToUpdate, "updated", response);
    }

    @PutMapping(path = "updateusermoney/{id}/{money}")
    public ResponseEntity<Object> updateUserMoneyById(@PathVariable("id") Long id, @PathVariable("money") double money) throws UserException {
        boolean response = userService.updateUserMoneyById(id, money);
        return giveResponse(getUserById(id), "update user money by id", response);
    }

    @GetMapping(path = "login/{username}/{password}")
    public ResponseEntity<Object> login(@PathVariable String username, @PathVariable String password) throws UserException {
        User user = userService.login(username, password);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path = "logout")
    public ResponseEntity<Object> logout() throws UserException {
        User logout = userService.logout();
        return new ResponseEntity<>(logout, HttpStatus.OK);
    }

    private ResponseEntity<Object> giveResponse(User user, String name, boolean response) throws UserException {
        if (response) {
            String message = "The user " + user.getUsername() + " has been " + name;
            ApiResponse apiResponse = new ApiResponse(message, HttpStatus.OK);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            String message = "The user " + user.getUsername() + " can't " + name;
            throw new UserException(message, HttpStatus.BAD_REQUEST);
        }
    }

}
