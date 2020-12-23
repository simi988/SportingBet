package com.example.sportingbet.repository;

import com.example.sportingbet.entity.EventDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<EventDO, Long> {

}
