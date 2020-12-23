package com.example.sportingbet.repository;

import com.example.sportingbet.entity.ParticipantDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ParticipantRepository extends CrudRepository<ParticipantDO, Long> {

    @Query("select participant from ParticipantDO participant where participant.name=?1")
    Optional<ParticipantDO> findByName(String participantDO);
}
