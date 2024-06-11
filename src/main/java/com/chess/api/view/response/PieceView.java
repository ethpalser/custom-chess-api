package com.chess.api.view.response;

import java.util.Set;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PieceView {

    private final String code;
    private final String colour;
    private final Set<PointView> allowedMoves;

}
