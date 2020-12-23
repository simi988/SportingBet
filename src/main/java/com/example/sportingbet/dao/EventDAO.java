package com.example.sportingbet.dao;

import com.example.sportingbet.exception.EventException;
import com.example.sportingbet.model.Event;

import java.util.List;

public interface EventDAO {
    void addEvent(Event event) throws EventException;

    List<Event> getAllEvents();
}
