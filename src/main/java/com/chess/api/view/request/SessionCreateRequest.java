package com.chess.api.view.request;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SessionCreateRequest {

    private final String whiteUsername;
    private final String blackUsername;

}
