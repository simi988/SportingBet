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
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
    public ResponseEntity<Object> insertUser(@Valid @NonNull @RequestBody User user) throws UserException {
        boolean response = userService.insertUser(user);
        return giveResponse(user, "added", response);
    }

    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping(path = "getuser/{id}")
    public User getUserById(@PathVariable("id") UUID id) throws UserException {
        return userService.getUserById(id);
    }

    @GetMapping(path = "getusermoney/{id}")
    public double getUserMoneyById(@PathVariable("id") UUID id) throws UserException {
        return userService.getUserMoneyById(id);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable("id") UUID id) throws UserException {
        boolean response = userService.deleteUser(id);
        return giveResponse(userService.getUserById(id), "deleted", response);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody User userToUpdate) throws UserException {
        boolean response = userService.updateUser(id, userToUpdate);
        return giveResponse(userToUpdate, "updated", response);
    }

    @PutMapping(path = "updateusermoney/{id}/{money}")
    public ResponseEntity<Object> updateUserMoneyById(@PathVariable("id") UUID id, @PathVariable("money") double money) throws UserException {
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
            String message = "The user " + user.getUserName() + " has " + name;
            ApiResponse apiResponse = new ApiResponse(message, HttpStatus.OK, ZonedDateTime.now(ZoneId.of("Z")));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            String message = "The user " + user.getUserName() + " can't " + name;
            throw new UserException(message, HttpStatus.BAD_REQUEST);
        }
    }

}
