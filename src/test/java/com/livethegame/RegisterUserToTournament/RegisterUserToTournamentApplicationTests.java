package com.livethegame.RegisterUserToTournament;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.livethegame.RegisterUserToTournament.common.TournamentUserResponseMapper;
import com.livethegame.RegisterUserToTournament.controller.RegisterUserToTournamentRestController;
import com.livethegame.RegisterUserToTournament.dto.TournamentUserRequest;
import com.livethegame.RegisterUserToTournament.dto.TournamentUserResponse;
import com.livethegame.RegisterUserToTournament.repository.UserRepository;
import com.livethegame.RegisterUserToTournament.repository.TournamentRepository;
import com.livethegame.RegisterUserToTournament.services.TournamentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Base64;

@WebMvcTest(RegisterUserToTournamentRestController.class)
@AutoConfigureMockMvc
class RegisterUserToTournamentApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TournamentRepository tournamentRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TournamentService tournamentService;
    @MockBean
    private TournamentUserResponseMapper tournamentUserResponseMapper;

    private static final String PASSWORD = "admin";
    private static final String Tournament = "admin";

    @Test
    public void testRegisterUserToTournament_Success() throws Exception {
        TournamentUserRequest request = new TournamentUserRequest();
        TournamentUserResponse response = new TournamentUserResponse();
        response.setId(1L);
        mockMvc.perform(MockMvcRequestBuilders.post("/user-to-tournament/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testRegisterUserToTournament_Business_TournamentNotFound() throws Exception {

    }
    @Test
    public void testRegisterUserToTournament_Business_UserNotFound() throws Exception {

    }

    @Test
    public void testCreateTournament_Business_RoleTypeNotFound() throws Exception {

    }

    @Test
    public void testCreateTournament_Business_WithoutAuthorization() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user-to-tournament/register")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void testCreateTournament_Business_testUnauthorizedAccess() throws Exception {
        Base64.Encoder encoder = Base64.getEncoder();
        String encodingParaUsuarioSinPermiso = encoder.encodeToString(("usuario" + ":" + "password").getBytes());
        mockMvc.perform(MockMvcRequestBuilders.get("/user-to-tournament/register")
                        .header("Authorization", "Basic " + encodingParaUsuarioSinPermiso)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
    private String asJsonString(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

}
