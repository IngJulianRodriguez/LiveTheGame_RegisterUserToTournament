package com.livethegame.RegisterUserToTournament.repository;

import com.livethegame.RegisterUserToTournament.entities.Tournaments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournaments, Long> {
}
