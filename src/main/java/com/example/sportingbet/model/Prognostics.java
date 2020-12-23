package com.example.sportingbet.model;

public class Prognostics {
    private Long id;

    private Odd odd;

    private double value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Odd getOdd() {
        return odd;
    }

    public void setOdd(Odd odd) {
        this.odd = odd;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }


}
