package com.example.sportingbet.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class User {
    private final UUID id;
    @NotBlank
    private final String name;
    private double money;

    public User(@JsonProperty("id") UUID id,
                @JsonProperty("name") String name,
                @JsonProperty("money") double money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public User setMoney(double money) {
        this.money = money;
        return null;
    }
}