package com.example.sportingbet.mapping;

import com.example.sportingbet.entity.EventDO;
import com.example.sportingbet.entity.ParticipantDO;
import com.example.sportingbet.entity.PrognosticsDO;
import com.example.sportingbet.model.Event;
import com.example.sportingbet.model.Participant;
import com.example.sportingbet.model.Prognostics;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class EventMapper {
    ParticipantMapper participantMapper = new ParticipantMapper();
    PrognosticsMapper prognosticsMapper = new PrognosticsMapper();

    public Event mapToDto(EventDO eventDO) {
        Event event = new Event();
        event.setId(eventDO.getId());

        List<ParticipantDO> participantsDOList = eventDO.getParticipants();
        List<Participant> participants = new ArrayList<>();
        for (ParticipantDO participantDO : participantsDOList) {
            Participant participant = participantMapper.mapToDto(participantDO);
            participants.add(participant);
        }
        event.setParticipants(participants);

        List<PrognosticsDO> prognosticsDOList = eventDO.getPrognostics();
        List<Prognostics> prognosticsList = new ArrayList<>();
        for (PrognosticsDO prognosticsDO : prognosticsDOList) {
            Prognostics prognostics = prognosticsMapper.mapToDto(prognosticsDO);
            prognosticsList.add(prognostics);
        }
        event.setPrognostics(prognosticsList);
        event.setDate(String.valueOf(eventDO.getDate()));
        event.setTime(String.valueOf(eventDO.getTime()));
        return event;
    }

    public EventDO mapToDO(Event event) {
        EventDO eventDO = new EventDO();

        List<Participant> participants = event.getParticipants();
        List<ParticipantDO> participantDOS = new ArrayList<>();

        for (Participant participant : participants) {
            ParticipantDO participantDO = participantMapper.mapToDO(participant);
            participantDOS.add(participantDO);
        }

        eventDO.setParticipants(participantDOS);

        List<Prognostics> prognosticsList = event.getPrognostics();
        List<PrognosticsDO> prognosticsDOS = new ArrayList<>();
        for (Prognostics prognostics : prognosticsList) {
            PrognosticsDO prognosticsDO = prognosticsMapper.mapToDO(prognostics);
            prognosticsDOS.add(prognosticsDO);
        }

        eventDO.setPrognostics(prognosticsDOS);
        eventDO.setDate(Date.valueOf(event.getDate()));
        eventDO.setTime(Time.valueOf(event.getTime()));
        return eventDO;
    }
}
