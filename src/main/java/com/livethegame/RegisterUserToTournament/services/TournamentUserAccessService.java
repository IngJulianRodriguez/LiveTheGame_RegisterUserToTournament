package com.livethegame.RegisterUserToTournament.services;

import com.livethegame.RegisterUserToTournament.Utils.Generator;
import com.livethegame.RegisterUserToTournament.dto.TournamentUserRequest;
import com.livethegame.RegisterUserToTournament.dto.TournamentUserResponse;
import com.livethegame.RegisterUserToTournament.entities.TournamentUsers;
import com.livethegame.RegisterUserToTournament.entities.TournamentUsersAccess;
import com.livethegame.RegisterUserToTournament.repository.TournamentRepository;
import com.livethegame.RegisterUserToTournament.repository.TournamentUserAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TournamentUserAccessService {

    @Autowired
    TournamentUserAccessRepository tournamentUserAccessRepository;

    public void registerUserAccess(TournamentUsers tournamentUser){
        TournamentUsersAccess tournamentUsersAccess = new TournamentUsersAccess();
        tournamentUsersAccess.setTournamentUser(tournamentUser);
        tournamentUsersAccess.setAccess_code(Generator.generateCode());

        tournamentUserAccessRepository.save(tournamentUsersAccess);

    }
}
