package org.ru.babidzhonio.pieces;

import org.ru.babidzhonio.Color;
import org.ru.babidzhonio.Coordinates;

import java.util.Set;

public class Queen extends LongRangePiece implements IRook, IBishop{
    public Queen(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMove() {
        Set<CoordinatesShift> moves = getBishopMove();
        moves.addAll(getRookMove());

        return moves;
    }
}
