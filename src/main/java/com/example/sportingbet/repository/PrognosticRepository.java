package com.example.sportingbet.repository;

import com.example.sportingbet.entity.PrognosticsDO;
import com.example.sportingbet.model.Odd;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PrognosticRepository extends CrudRepository<PrognosticsDO, Long> {

    @Query("select prognosticsDO from PrognosticsDO prognosticsDO where prognosticsDO.odd=?1")
    Optional<PrognosticsDO> findByOdd(Odd participantDO);
}

