package com.example.sportingbet.mapping;

import com.example.sportingbet.entity.ParticipantDO;
import com.example.sportingbet.model.Participant;

public class ParticipantMapper {

    public Participant mapToDto(ParticipantDO participantDO) {
        Participant participant = new Participant();
        participant.setId(participantDO.getId());
        participant.setName(participantDO.getName());
        return participant;
    }

    public ParticipantDO mapToDO(Participant participant) {
        ParticipantDO participantDO = new ParticipantDO();
        participantDO.setName(participant.getName());
        return participantDO;
    }

}
