package com.chess.api.view.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginView {

    private final String jwt;
    private final String refreshToken;
}
