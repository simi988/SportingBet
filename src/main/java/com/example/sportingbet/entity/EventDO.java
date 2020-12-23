package com.example.sportingbet.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "event")
public class EventDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "event_participants",
            joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id", referencedColumnName = "id")
    )
    private List<ParticipantDO> participants;


    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "event_prognostics",
            joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "prognostics_id", referencedColumnName = "id")
    )
    private List<PrognosticsDO> prognostics;

    @Column(name = "date")
    private Date date;

    @Column(name = "time")
    private Time time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ParticipantDO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantDO> participants) {
        this.participants = participants;
    }

    public List<PrognosticsDO> getPrognostics() {
        return prognostics;
    }

    public void setPrognostics(List<PrognosticsDO> prognostics) {
        this.prognostics = prognostics;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}