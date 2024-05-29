package com.livethegame.RegisterUserToTournament.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
public class TournamentUsersAccess {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence_tournamentusersaccess", allocationSize = 1)
    private long id;
    private String access_code;
    private String url_qr;
    private boolean is_active;
    private LocalDateTime date_created;
    private LocalDateTime last_updated;
    @ManyToOne
    @JoinColumn(name = "tournament_user_id")
    private TournamentUsers tournamentUser;

    public TournamentUsersAccess() {
        this.setDate_created();
        this.setLast_updated();
        this.setIs_active();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean is_active() {
        return is_active;
    }

    private void setIs_active() {
        this.is_active = true;
    }

    public LocalDateTime getDate_created() {
        return date_created;
    }

    private void setDate_created() {
        ZoneId easternTime = ZoneId.of("America/Bogota");
        this.date_created = LocalDateTime.now(easternTime);;
    }

    public LocalDateTime getLast_updated() {
        return last_updated;
    }

    private void setLast_updated() {
        this.last_updated = this.getDate_created();
    }

    public String getAccess_code() {
        return access_code;
    }

    public void setAccess_code(String access_code) {
        this.access_code = access_code;
    }

    public String getUrl_qr() {
        return url_qr;
    }

    public void setUrl_qr(String url_qr) {
        this.url_qr = url_qr;
    }

    public TournamentUsers getTournamentUser() {
        return tournamentUser;
    }

    public void setTournamentUser(TournamentUsers tournamentUser) {
        this.tournamentUser = tournamentUser;
    }
}
