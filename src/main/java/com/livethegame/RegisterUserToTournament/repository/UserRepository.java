package com.livethegame.RegisterUserToTournament.repository;

import com.livethegame.RegisterUserToTournament.entities.Categories;
import com.livethegame.RegisterUserToTournament.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
