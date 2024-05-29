package com.livethegame.RegisterUserToTournament.repository;

import com.livethegame.RegisterUserToTournament.entities.Params;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParamsRepository extends JpaRepository<Params, Long> {
    Optional<Params> findByName(String name);
}
