package com.livethegame.RegisterUserToTournament.Utils;

import com.livethegame.RegisterUserToTournament.dto.TournamentUserRequest;
import com.livethegame.RegisterUserToTournament.entities.*;

public  class Mapper {
    public static TournamentUsers TournamentUserRequestToTournamentUser(Users user, Tournaments tournament, Roles role){
        TournamentUsers tournamentUsers = new TournamentUsers();
        tournamentUsers.setUser(user);
        tournamentUsers.setTournament(tournament);
        tournamentUsers.setRole(role);
        return tournamentUsers;
    };
}
