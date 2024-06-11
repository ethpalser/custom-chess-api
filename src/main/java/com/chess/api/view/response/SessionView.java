package com.chess.api.view.response;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SessionView {

    private final UUID id;
    private final PlayerView playerWhite;
    private final PlayerView playerBlack;
    private final List<ActionView> moves;
    private final Map<PointView, PieceView> board;
    private final PlayerView winner;

}
