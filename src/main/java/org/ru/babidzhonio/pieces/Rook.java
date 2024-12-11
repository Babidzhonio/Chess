package org.ru.babidzhonio.pieces;

import org.ru.babidzhonio.Color;
import org.ru.babidzhonio.Coordinates;

import java.util.Set;

public class Rook extends LongRangePiece implements IRook{
    public Rook(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }
    @Override
    protected Set<CoordinatesShift> getPieceMove() {
        return getRookMove();
    }
}
