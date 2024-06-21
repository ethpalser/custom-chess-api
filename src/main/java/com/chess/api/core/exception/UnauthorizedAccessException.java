package com.chess.api.core.exception;

import lombok.Getter;

public class UnauthorizedAccessException extends IllegalAccessException {

    @Getter
    private final String message;

    public UnauthorizedAccessException() {
        this.message = "unauthorized access";
    }

    public UnauthorizedAccessException(String message) {
        this.message = message;
    }

}
