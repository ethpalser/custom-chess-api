package com.chess.api.view.response;

import java.util.Objects;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PointView {

    private final int x;
    private final int y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointView pointView = (PointView) o;
        return x == pointView.x && y == pointView.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
