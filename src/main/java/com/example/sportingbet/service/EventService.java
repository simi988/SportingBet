package com.example.sportingbet.service;

import com.example.sportingbet.exception.EventException;
import com.example.sportingbet.model.Event;

import java.util.List;

public interface EventService {
    void addEvent(Event event) throws EventException;

    List<Event> getAllEvents();
}
