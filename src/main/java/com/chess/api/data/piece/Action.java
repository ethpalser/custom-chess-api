package com.chess.api.data.piece;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Action {

    private final Piece piece;
    private final Point start;
    private final Point end;

}
