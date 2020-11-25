package com.example.sportingbet.model;

public class LoginUser {

    private static User user;

    private LoginUser() {
    }

    public static User getUser() {
        return LoginUser.user;
    }

    public static void setUser(User user) {
       LoginUser.user = user;
    }
}
