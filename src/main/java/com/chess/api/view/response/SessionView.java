package com.chess.api.view.response;

import com.chess.api.data.piece.Action;
import com.chess.api.data.piece.Point;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SessionView {

    private final PlayerView playerWhite;
    private final PlayerView playerBlack;
    private final List<Action> moves;
    private final Map<Point, PieceView> board;
    private final PlayerView winner;

}
