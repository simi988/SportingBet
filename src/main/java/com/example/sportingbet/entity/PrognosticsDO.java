package com.example.sportingbet.entity;

import com.example.sportingbet.model.Odd;

import javax.persistence.*;

@Entity
@Table(name = "prognostics")
public class PrognosticsDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Enumerated(EnumType.ORDINAL)
    private Odd odd;
    @Column(name = "value")
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
