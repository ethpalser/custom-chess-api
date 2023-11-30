package com.chess.api.model.piece;

import com.chess.api.model.Colour;
import com.chess.api.model.Coordinate;
import com.chess.api.model.movement.ExtraMovement;
import com.chess.api.model.movement.Movement;
import com.chess.api.model.movement.MovementType;
import com.chess.api.model.movement.Path;
import com.chess.api.model.movement.condition.Condition;
import com.chess.api.model.movement.condition.Location;
import com.chess.api.model.movement.condition.Property;
import com.chess.api.model.movement.condition.PropertyState;
import com.chess.api.model.movement.condition.Reference;
import java.util.List;

public class PieceFactory {

    private static PieceFactory factory;

    private PieceFactory() {
    }

    public static PieceFactory getInstance() {
        if (factory == null) {
            factory = new PieceFactory();
        }
        return factory;
    }

    public Piece build(PieceType type, Colour colour, Coordinate coordinate) {
        // Common and basic conditions
        Condition noCapture = Condition.builder()
                .reference(new Reference(Location.AT_DESTINATION))
                .propertyState(PropertyState.DOES_NOT_EXIST)
                .build();
        Condition onlyCapture = Condition.builder()
                .reference(new Reference(Location.AT_DESTINATION))
                .property(new Property<>("colour"))
                .propertyState(PropertyState.OPPOSITE)
                .build();
        Condition selfNotMoved = Condition.builder()
                .reference(new Reference(Location.AT_START))
                .property(new Property<>("hasMoved"))
                .propertyState(PropertyState.FALSE)
                .build();

        Path vertical = new Path(Coordinate.at(0, 1), Coordinate.at(0, Coordinate.MAX_Y));
        Path horizontal = new Path(Coordinate.at(1, 0), Coordinate.at(Coordinate.MAX_X, 0));
        Path diagonal = new Path(Coordinate.at(1, 1), Coordinate.at(Coordinate.MAX_X, Coordinate.MAX_Y));

        switch (type) {
            case KNIGHT -> {
                Movement knightBaseMove1 = new Movement(new Path(Coordinate.at(1, 2)), MovementType.JUMP, true, true);
                Movement knightBaseMove2 = new Movement(new Path(Coordinate.at(2, 1)), MovementType.JUMP, true, true);
                return new Piece(PieceType.KNIGHT, colour, coordinate, knightBaseMove1, knightBaseMove2);
            }
            case ROOK -> {
                Movement rookBaseMoveV = new Movement(vertical, MovementType.ADVANCE, true, false);
                Movement rookBaseMoveH = new Movement(horizontal, MovementType.ADVANCE, false, true);
                return new Piece(PieceType.ROOK, colour, coordinate, rookBaseMoveV, rookBaseMoveH);
            }
            case BISHOP -> {
                Movement bishopBaseMove = new Movement(diagonal, MovementType.ADVANCE, true, true);
                return new Piece(PieceType.BISHOP, colour, coordinate, bishopBaseMove);
            }
            case QUEEN -> {
                Movement queenBaseMoveV = new Movement(vertical, MovementType.ADVANCE, true, false);
                Movement queenBaseMoveH = new Movement(horizontal, MovementType.ADVANCE, false, true);
                Movement queenBaseMoveD = new Movement(diagonal, MovementType.ADVANCE, true, true);
                return new Piece(PieceType.QUEEN, colour, coordinate, queenBaseMoveV, queenBaseMoveH, queenBaseMoveD);
            }
            case KING -> {
                Movement kingBaseMoveV = new Movement(new Path(Coordinate.at(0, 1)), MovementType.ADVANCE, true, false);
                Movement kingBaseMoveH = new Movement(new Path(Coordinate.at(1, 0)), MovementType.ADVANCE, false, true);
                Movement kingBaseMoveD = new Movement(new Path(Coordinate.at(1, 1)), MovementType.ADVANCE, true, true);

                Condition castleKingSideCond2 = Condition.builder()
                        .reference(new Reference(Location.AT_COORDINATE, Coordinate.at(7, 0)))
                        .property(new Property<>("hasMoved"))
                        .propertyState(PropertyState.FALSE)
                        .build();
                Condition castleKingSideCond3 = Condition.builder()
                        .reference(new Reference(Location.PATH_TO_COORDINATE, Coordinate.at(7, 0)))
                        .propertyState(PropertyState.DOES_NOT_EXIST)
                        .build();
                ExtraMovement kingSideRookMovement = new ExtraMovement(Coordinate.at(7, 0), Coordinate.at(5, 0));

                Movement castleKingSide = new Movement(new Path(Coordinate.at(2, 0)), MovementType.ADVANCE, false,
                        false, true, List.of(selfNotMoved, castleKingSideCond2, castleKingSideCond3),
                        kingSideRookMovement);

                Condition castleQueenSideCond2 = Condition.builder()
                        .reference(new Reference(Location.AT_COORDINATE, Coordinate.at(0, 0)))
                        .property(new Property<>("hasMoved"))
                        .propertyState(PropertyState.FALSE)
                        .build();
                Condition castleQueenSideCond3 = Condition.builder()
                        .reference(new Reference(Location.PATH_TO_COORDINATE, Coordinate.at(0, 0)))
                        .propertyState(PropertyState.DOES_NOT_EXIST)
                        .build();
                ExtraMovement queenSideRookMovement = new ExtraMovement(Coordinate.at(0, 0), Coordinate.at(3, 0));

                Movement castleQueenSide = new Movement(new Path(Coordinate.at(2, 0)), MovementType.ADVANCE, false,
                        true, true, List.of(selfNotMoved, castleQueenSideCond2, castleQueenSideCond3),
                        queenSideRookMovement);

                return new Piece(PieceType.KING, colour, coordinate, kingBaseMoveV, kingBaseMoveH, kingBaseMoveD,
                        castleKingSide, castleQueenSide);
            }
            case PAWN -> {
                Movement pawnBaseMove = new Movement(new Path(Coordinate.at(0, 1)), MovementType.ADVANCE, false,
                        false, false, List.of(noCapture));
                Movement fastAdvance = new Movement(new Path(Coordinate.at(0, 1), Coordinate.at(0, 2)),
                        MovementType.ADVANCE, false, false, true, List.of(selfNotMoved, noCapture));

                Movement capture = new Movement(new Path(Coordinate.at(1, 1)), MovementType.ADVANCE, false, true, false,
                        List.of(onlyCapture));

                Condition enPassantCond1 = Condition.builder()
                        .reference(new Reference(Location.LAST_MOVED))
                        .property(new Property<>("type"))
                        .propertyState(PropertyState.EQUAL)
                        .expected(PieceType.PAWN)
                        .build();
                Condition enPassantCond2 = Condition.builder()
                        .reference(new Reference(Location.LAST_MOVED))
                        .propertyState(PropertyState.EQUAL)
                        .compare(new Reference(Location.BELOW_DESTINATION))
                        .build();
                Condition enPassantCond3 = Condition.builder()
                        .reference(new Reference(Location.LAST_MOVED))
                        .property(new Property<>("lastMoveDistance"))
                        .propertyState(PropertyState.EQUAL)
                        .expected(2)
                        .build();

                ExtraMovement extraMovement = new ExtraMovement(new Reference(Location.BELOW_DESTINATION),
                        null, null, false);
                Movement enPassant = new Movement(new Path(Coordinate.at(1, 1)), MovementType.ADVANCE, false, true,
                        false, List.of(noCapture, enPassantCond1, enPassantCond2, enPassantCond3), extraMovement);
                return new Piece(PieceType.PAWN, colour, coordinate, pawnBaseMove, fastAdvance, capture, enPassant);
            }
        }
        return new Piece();
    }

}
