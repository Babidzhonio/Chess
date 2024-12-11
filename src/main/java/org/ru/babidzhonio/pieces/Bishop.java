package org.ru.babidzhonio.pieces;

import org.ru.babidzhonio.Color;
import org.ru.babidzhonio.Coordinates;

import java.util.Set;

public class Bishop extends LongRangePiece implements IBishop{
    public Bishop(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMove() {
        return getBishopMove();
    }
}
