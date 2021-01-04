package com.example.sportingbet.repository;

import com.example.sportingbet.entity.BetDO;
import org.springframework.data.repository.CrudRepository;

public interface BetRepository extends CrudRepository<BetDO, Long> {
}
