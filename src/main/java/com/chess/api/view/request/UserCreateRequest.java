package com.chess.api.view.request;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserCreateRequest {

    private final String username;
    private final String password;

}
