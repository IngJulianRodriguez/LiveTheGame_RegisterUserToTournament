package com.livethegame.RegisterUserToTournament.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel()
public class TournamentUserResponse {

    @ApiModelProperty(name = "Id", required = true,example = "", value = "")
    private Long id;

    public TournamentUserResponse(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
