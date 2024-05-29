package com.livethegame.RegisterUserToTournament.repository;

import com.livethegame.RegisterUserToTournament.entities.TournamentTypes;
import com.livethegame.RegisterUserToTournament.entities.TournamentUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TournamentUserRepository extends JpaRepository<TournamentUsers, Long> {
    List<TournamentUsers> findByTournamentIdAndRoleId(Long id, Long roleId);
}
