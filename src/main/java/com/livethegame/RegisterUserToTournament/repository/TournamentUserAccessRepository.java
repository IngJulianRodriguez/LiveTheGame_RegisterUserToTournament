package com.livethegame.RegisterUserToTournament.repository;

import com.livethegame.RegisterUserToTournament.entities.TournamentUsersAccess;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TournamentUserAccessRepository extends JpaRepository<TournamentUsersAccess, Long> {
}
