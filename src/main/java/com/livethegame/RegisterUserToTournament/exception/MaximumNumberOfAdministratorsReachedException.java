package com.livethegame.RegisterUserToTournament.exception;

public class MaximumNumberOfAdministratorsReachedException extends RuntimeException {
    public MaximumNumberOfAdministratorsReachedException(String message) {
        super(message);
    }
}
