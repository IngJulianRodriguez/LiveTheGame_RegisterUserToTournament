package com.livethegame.RegisterUserToTournament.repository;

import com.livethegame.RegisterUserToTournament.entities.Categories;
import com.livethegame.RegisterUserToTournament.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Long> {
}
