package org.ru.babidzhonio;

import org.ru.babidzhonio.board.Vertical;
import org.ru.babidzhonio.pieces.CoordinatesShift;

import java.util.Objects;

public class Coordinates {
    public final Integer horizontal;
    public final Vertical vertical;

    @Override
    public String toString() {
        return "Coordinates{" +
                "horizontal=" + horizontal +
                ", vertical=" + vertical +
                '}';
    }

    public Coordinates(Vertical vertical, Integer horizontal) {
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public Coordinates shift(CoordinatesShift shift){
        return new Coordinates(Vertical.values()[this.vertical.ordinal() + shift.verticalShift], this.horizontal + shift.horizontalShift);
    }

    public boolean canShift(CoordinatesShift shift){
        int v = vertical.ordinal() + shift.verticalShift;
        int h = horizontal + shift.horizontalShift;

        if (v < 0 || v > 7){
            return false;
        }
        if (h < 1 || v > 8){
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(vertical, that.vertical) && horizontal == that.horizontal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertical, horizontal);
    }
}
