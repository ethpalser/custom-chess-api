package com.chess.api.view.response;

import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PieceView {

    private final UUID id;
    private final String colour;
    private final Set<PointView> allowedMoves;

}
