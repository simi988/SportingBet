package com.example.sportingbet.dao;

import com.example.sportingbet.entity.EventDO;
import com.example.sportingbet.entity.ParticipantDO;
import com.example.sportingbet.entity.PrognosticsDO;
import com.example.sportingbet.exception.EventException;
import com.example.sportingbet.mapping.EventMapper;
import com.example.sportingbet.model.Event;
import com.example.sportingbet.repository.EventRepository;
import com.example.sportingbet.repository.ParticipantRepository;
import com.example.sportingbet.repository.PrognosticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("eventdao")
public class EventDAOImpl implements EventDAO {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    ParticipantRepository participantRepository;
    @Autowired
    PrognosticRepository prognosticRepository;
    private final EventMapper eventMapper = new EventMapper();

    @Override
    public void addEvent(Event event) throws EventException {
        EventDO eventDO = eventMapper.mapToDO(event);
        eventDO.setParticipants(checkParticipants(eventDO));
        eventDO.setPrognostics(checkPrognostics(eventDO));
        eventRepository.save(eventDO);

    }

    @Override
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        Iterable<EventDO> all = eventRepository.findAll();
        for (EventDO eventDO : all) {
            Event event = eventMapper.mapToDto(eventDO);
            events.add(event);
        }
        return events;
    }

    private List<ParticipantDO> checkParticipants(EventDO eventDO) throws EventException {

        List<ParticipantDO> participantDos = new ArrayList<>();

        for (ParticipantDO participant : eventDO.getParticipants()) {
            Optional<ParticipantDO> optionalParticipantDO = participantRepository.findByName(participant.getName());

            if (optionalParticipantDO.isPresent()) {
                try {
                    participantDos.add(optionalParticipantDO.get());
                } catch (ClassCastException exception) {
                    throw new EventException(exception.getMessage(), HttpStatus.BAD_REQUEST);
                }

            } else {
                participantDos.add(participant);
            }
        }
        return participantDos;
    }

    private List<PrognosticsDO> checkPrognostics(EventDO eventDO) {

        List<PrognosticsDO> prognosticsDOList = new ArrayList<>();

        for (PrognosticsDO prognostic : eventDO.getPrognostics()) {
            Optional<PrognosticsDO> optionalPrognosticsDO = prognosticRepository.findByOdd(prognostic.getOdd());

            if (optionalPrognosticsDO.isPresent() && prognostic.getValue() == optionalPrognosticsDO.get().getValue()) {
                prognosticsDOList.add(optionalPrognosticsDO.get());

            } else {
                prognosticsDOList.add(prognostic);
            }
        }
        return prognosticsDOList;
    }

}
