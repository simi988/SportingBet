package com.example.sportingbet.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class User {
    private Long id;
    @NotBlank
    private String name;
    private double money;
    @NotBlank
    private String username;
    @NotBlank
    private String password;


    public User(
            @JsonProperty("id") Long id,
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

    public User() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
