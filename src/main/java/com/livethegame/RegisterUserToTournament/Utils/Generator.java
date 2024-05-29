package com.livethegame.RegisterUserToTournament.Utils;

import com.livethegame.RegisterUserToTournament.entities.Roles;
import com.livethegame.RegisterUserToTournament.entities.TournamentUsers;
import com.livethegame.RegisterUserToTournament.entities.Tournaments;
import com.livethegame.RegisterUserToTournament.entities.Users;

public  class Generator {
    public static String generateCode(){
        double code = 100000 + Math.random() * 900000;
        return String.valueOf(code);
    };
}
