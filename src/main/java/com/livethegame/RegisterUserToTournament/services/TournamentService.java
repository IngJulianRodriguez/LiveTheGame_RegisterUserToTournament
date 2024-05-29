package com.livethegame.RegisterUserToTournament.services;

import com.livethegame.RegisterUserToTournament.Utils.Mapper;
import com.livethegame.RegisterUserToTournament.common.TournamentUserResponseMapper;
import com.livethegame.RegisterUserToTournament.dto.TournamentUserRequest;
import com.livethegame.RegisterUserToTournament.dto.TournamentUserResponse;
import com.livethegame.RegisterUserToTournament.entities.*;
import com.livethegame.RegisterUserToTournament.exception.*;
import com.livethegame.RegisterUserToTournament.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TournamentService {

    @Autowired
    TournamentRepository tournamentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ParamsRepository paramsRepository;
    @Autowired
    TournamentUserRepository tournamentUserRepository;
    @Autowired
    TournamentUserAccessService tournamentUserAccessService;
    @Autowired
    TournamentUserResponseMapper tournamentUserResponseMapper;


    public TournamentUserResponse registerUser(TournamentUserRequest tournamentUserRequest){
        Optional<Users> optionalUser = userRepository.findById(tournamentUserRequest.getUser_id());
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("Usuario no encontrado con ID: " + tournamentUserRequest.getUser_id());
        }
        Optional<Tournaments> optionalTournament = tournamentRepository.findById(tournamentUserRequest.getTournament_id());
        if (optionalTournament.isEmpty()) {
            throw new TournamentNotFoundException("Torneo no encontrado con ID: " + tournamentUserRequest.getTournament_id());
        }
        Optional<Roles> optionalRole = roleRepository.findById(tournamentUserRequest.getRole_id());
        if (optionalRole.isEmpty()) {
            throw new RoleNotFoundException("Rol no encontrado con ID: " + tournamentUserRequest.getRole_id());
        }
        Optional<Params> optionalParamsTournamentAdminId = paramsRepository.findByName("tournament.admin.id");
        if (optionalParamsTournamentAdminId.isEmpty()) {
            throw new ParamsNotFoundException("Parametro tournament.admin.id no encontrado");
        }
        long paramsTournamentAdminId = optionalParamsTournamentAdminId.get().getValueAsLong();
        if(paramsTournamentAdminId == tournamentUserRequest.getRole_id()){
            if(maximumNumberOfAdministratorsReached(tournamentUserRequest.getTournament_id(), paramsTournamentAdminId)){
                throw new MaximumNumberOfAdministratorsReachedException("Maximo numero de administradores alcanzado");
            }
        }
        TournamentUsers tournamentUserRequestToTournamentUser = Mapper.TournamentUserRequestToTournamentUser(optionalUser.get(), optionalTournament.get(), optionalRole.get());
        TournamentUsers save = tournamentUserRepository.save(tournamentUserRequestToTournamentUser);
        TournamentUserResponse tournamentToTournamentUserResponse = tournamentUserResponseMapper.TournamentUserToTournamentUserResponse(save);

        Optional<Params> optionalParamsTournamentPlayerId = paramsRepository.findByName("tournament.player.id");
        if (optionalParamsTournamentPlayerId.isEmpty()) {
            throw new ParamsNotFoundException("Parametro tournament.player.id no encontrado");
        }
        if(optionalParamsTournamentPlayerId.get().getValueAsLong()
                == tournamentUserRequest.getRole_id()){
            tournamentUserAccessService.registerUserAccess(save);
        }
        return tournamentToTournamentUserResponse;

    }

    private boolean maximumNumberOfAdministratorsReached(Long tournamentId, Long paramsTournamentAdminId){
        Optional<Params> optionalParamsTournamentAdminsMax = paramsRepository.findByName("tournament.admins.max");
        if (optionalParamsTournamentAdminsMax.isEmpty()) {
            throw new ParamsNotFoundException("Parametro tournament.admins.max no encontrado");
        }
        List<TournamentUsers> tournamentUsersList = tournamentUserRepository.findByTournamentIdAndRoleId(tournamentId, paramsTournamentAdminId);
        return optionalParamsTournamentAdminsMax.get().getValueAsInt() == tournamentUsersList.size();
    }

}
