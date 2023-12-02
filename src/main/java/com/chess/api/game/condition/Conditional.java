package com.chess.api.game.condition;

import com.chess.api.game.Board;
import com.chess.api.game.movement.Action;

/**
 * Conditional describes an object that has conditions, rule or criteria, that it contains. The criteria will take
 * the current state of the board and the action being attempted to determine if the expected criteria is met.
 */
public interface Conditional {

    boolean isExpected(Board board, Action action);

}
