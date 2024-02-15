package com.chess.api.game.condition;

import com.chess.api.game.Board;
import com.chess.api.game.movement.Action;
import com.chess.api.game.piece.Piece;
import com.chess.api.game.reference.Reference;
import java.util.List;
import lombok.NonNull;

public class PropertyCondition implements Conditional {

    private final Reference reference;
    private final Property<Piece> property;
    private final Comparator comparator;
    private final Object expected;

    public PropertyCondition(@NonNull Reference reference, @NonNull Comparator comparator) {
        this(reference, comparator, null, null);
    }

    public PropertyCondition(@NonNull Reference reference, @NonNull Comparator comparator, Property<Piece> property,
            Object expected) {
        if (property == null && !Comparator.canReferenceSelf(comparator)) {
            throw new IllegalArgumentException("Cannot use a Comparator that requires an expected value.");
        }
        this.reference = reference;
        this.comparator = comparator;
        this.property = property;
        this.expected = expected;
    }

    @Override
    public boolean isExpected(Board board, Action action) {
        List<Piece> list = this.reference.getPieces(board, action);

        boolean hasPiece = false;
        for (Piece piece : list) {
            if (piece == null) {
                continue;
            }
            hasPiece = true;
            Object pieceProperty = this.property != null ? this.property.fetch(piece) : null;
            if (!isExpectedState(pieceProperty)) {
                return false;
            }
        }
        return hasPiece || Comparator.DOES_NOT_EXIST.equals(comparator);
    }

    private boolean isExpectedState(Object objProperty) {
        switch (this.comparator) {
            case EXIST -> {
                return objProperty != null;
            }
            case DOES_NOT_EXIST -> {
                return objProperty == null;
            }
            case FALSE -> {
                return Boolean.FALSE.equals(objProperty);
            }
            case TRUE -> {
                return Boolean.TRUE.equals(objProperty);
            }
            case EQUAL -> {
                return (this.expected == null && objProperty == null) || (this.expected != null && objProperty != null
                        && objProperty.getClass().equals(this.expected.getClass()) && objProperty.equals(this.expected));
            }
            case NOT_EQUAL -> {
                return (this.expected == null && objProperty != null) || (objProperty != null
                        && !objProperty.equals(this.expected));
            }
            default -> {
                return false;
            }
        }
    }

    @Override
    public String toString() {
        return "PropertyCondition{" +
                "reference=" + reference +
                ", property=" + property +
                ", comparator=" + comparator +
                ", expected=" + expected +
                '}';
    }
}
