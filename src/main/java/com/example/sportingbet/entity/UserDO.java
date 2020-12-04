package com.example.sportingbet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserDO {
    @Id
   private final Long id;
    @Column
   private final String name;
    @Column
    private final double money;
    @Column
    private final   String username;
    @Column
    private final String password;

    public UserDO(Long id, String name, double money, String username, String password) {
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
