package com.example.sportingbet.service;

import com.example.sportingbet.dao.EventDAO;
import com.example.sportingbet.exception.EventException;
import com.example.sportingbet.model.Event;
import com.example.sportingbet.model.Participant;
import com.example.sportingbet.model.Prognostics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventDAO eventDao;


    @Autowired
    public EventServiceImpl(@Qualifier("eventdao") EventDAO eventDao) {
        this.eventDao = eventDao;
    }

    @Override
    public void addEvent(Event event) throws EventException {
        try {

            checkParticipants(event.getParticipants());
            checkPrognostics(event.getPrognostics());
            eventDao.addEvent(event);
        } catch (DataIntegrityViolationException exception) {
            throw new EventException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Event> getAllEvents() {
        return eventDao.getAllEvents();
    }

    private void checkParticipants(List<Participant> participants) throws EventException {

        if (participants.size() > 2) {
            throw new EventException("Only two participants per event", HttpStatus.BAD_REQUEST);
        } else if (participants.size() <= 1) {
            throw new EventException("We need two participants per event", HttpStatus.BAD_REQUEST);
        }
    }

    private void checkPrognostics(List<Prognostics> prognostics) throws EventException {
        Prognostics prognostic = prognostics.get(0);
        if (prognostics.size() > 1) {
            throw new EventException("Only one prognostics per event", HttpStatus.BAD_REQUEST);
        } else if (prognostic.getValue() <= 0) {
            throw new EventException("The value must to be bigger that 0", HttpStatus.BAD_REQUEST);
        }
    }

}
