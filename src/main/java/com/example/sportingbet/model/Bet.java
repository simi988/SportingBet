package com.example.sportingbet.model;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

public class Bet {

    private Long id;
    @NotEmpty
    Map<Long, Long> eventList;
    private double sum;
    private double odd;


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
