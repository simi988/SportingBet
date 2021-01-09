package com.example.sportingbet.entity;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "bet")
public class BetDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ElementCollection
    @CollectionTable(
            name = "bet_event",
            joinColumns = @JoinColumn(name = "bet_id", referencedColumnName = "id")
    )
    @MapKeyJoinColumn(name = "event_id", referencedColumnName = "id")
    Map<Long, Long> eventList;

    @Column(name = "sum")
    private double sum;

    @Column(name = "odd")
    private double odd;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private UserDO user;

    private boolean win;

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public UserDO getUser() {
        return user;
    }

    public void setUser(UserDO user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Long, Long> getEventList() {
        return eventList;
    }

    public void setEventList(Map<Long, Long> eventList) {
        this.eventList = eventList;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getOdd() {
        return odd;
    }

    public void setOdd(double odd) {
        this.odd = odd;
    }
}
