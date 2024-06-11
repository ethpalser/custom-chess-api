package com.chess.api.view.response;

import java.util.UUID;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PlayerView {

    private final UUID id;
    private final String colour;
    private final String username;

}
