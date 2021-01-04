package com.example.sportingbet.repository;

import com.example.sportingbet.entity.BetDO;
import com.example.sportingbet.entity.UserDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserDO, Long> {

    @Query("select user from UserDO user where user.username=?1")
    Optional<UserDO> findByName(String username);

    @Query("select user.betDO from UserDO user where user.username=?1")
    Optional<BetDO> findAllBets(String username);
}
