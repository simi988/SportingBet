package com.example.sportingbet.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class User {
    private final Long id;
    @NotBlank
    private final String name;
    private double money;
    @NotBlank
    private final String username;
    @NotBlank
    private final String password;


    public User(@JsonProperty("id") Long id,
                @JsonProperty("name") String name,
                @JsonProperty("money") double money,
                @JsonProperty("username") String username,
                @JsonProperty("password") String password) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.username = username;
        this.password = password;
    }




    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public User setMoney(double money) {
        this.money = money;
        return null;
    }
}
