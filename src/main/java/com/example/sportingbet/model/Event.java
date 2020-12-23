package com.example.sportingbet.model;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class Event {
    private Long id;
    @NotEmpty
    private List<Participant> participants;
    @NotEmpty
    private List<Prognostics> prognostics;
    private String date;
    private String time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public List<Prognostics> getPrognostics() {
        return prognostics;
    }

    public void setPrognostics(List<Prognostics> prognostics) {
        this.prognostics = prognostics;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
