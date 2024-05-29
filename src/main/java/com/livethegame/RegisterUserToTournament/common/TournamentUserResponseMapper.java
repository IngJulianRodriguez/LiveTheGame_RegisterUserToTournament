package com.livethegame.RegisterUserToTournament.common;

import com.livethegame.RegisterUserToTournament.dto.TournamentUserResponse;
import com.livethegame.RegisterUserToTournament.entities.TournamentUsers;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TournamentUserResponseMapper {
    TournamentUserResponse TournamentUserToTournamentUserResponse(TournamentUsers source);
}
