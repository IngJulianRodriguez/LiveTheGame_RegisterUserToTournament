package com.livethegame.RegisterUserToTournament.controller;

import com.livethegame.RegisterUserToTournament.dto.TournamentUserRequest;
import com.livethegame.RegisterUserToTournament.dto.TournamentUserResponse;
import com.livethegame.RegisterUserToTournament.exception.*;
import com.livethegame.RegisterUserToTournament.services.MonitoringService;
import com.livethegame.RegisterUserToTournament.services.TournamentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Api Register User To Tournament")
@RestController
@RequestMapping("/user-to-tournament")
public class RegisterUserToTournamentRestController {

    @Autowired
    TournamentService tournamentService;
    @Autowired
    MonitoringService monitoringService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody TournamentUserRequest input) {
        String message = "";
        try {
            TournamentUserResponse TournamentUserResponse = tournamentService.registerUser(input);
            monitoringService.registerSuccessLog(String.valueOf(input.getUser_id()),"/register "+input.toString()+" "+TournamentUserResponse);
            return ResponseEntity.ok(TournamentUserResponse);
        } catch (UserNotFoundException
                 | TournamentNotFoundException
                 | RoleNotFoundException
                 | ParamsNotFoundException e) {
            monitoringService.registerControlledExceptionLog("","/register "+input.toString()+" "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (MaximumNumberOfAdministratorsReachedException e) {
            monitoringService.registerControlledExceptionLog("","/register "+input.toString()+" "+e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            monitoringService.registerNotControlledExceptionLog("","/register "+input.toString()+" "+e.getMessage());
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
    @GetMapping("/test-register")
    public ResponseEntity<?> testRegister(){
        return ResponseEntity.ok().build();
    }
}
