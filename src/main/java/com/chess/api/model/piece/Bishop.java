package com.chess.api.model.piece;

import com.chess.api.model.Colour;
import com.chess.api.model.Coordinate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Bishop implements Piece {

    private final Colour colour;
    private Coordinate coordinate;

    @Getter(AccessLevel.NONE)
    private boolean hasMoved;

    public Bishop(@NonNull Colour colour, @NonNull Coordinate coordinate) {
        this.coordinate = coordinate;
        this.colour = colour;
        this.hasMoved = false;
    }

    public void setCoordinate(Coordinate next) {
        if (this.coordinate.getX() == next.getX() && this.coordinate.getY() == next.getY()) {
            return;
        }
        this.coordinate = next;
        this.hasMoved = true;
    }

    @Override
    public boolean getHasMoved() {
        return hasMoved;
    }
}
