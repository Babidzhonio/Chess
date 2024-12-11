package org.ru.babidzhonio.pieces;

import org.ru.babidzhonio.board.Board;
import org.ru.babidzhonio.Color;
import org.ru.babidzhonio.Coordinates;

import java.util.HashSet;
import java.util.Set;

public class King extends Piece {
    public King(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMove() {
        Set<CoordinatesShift> result = new HashSet<>();

        for (int verticalShift = -1; verticalShift <= 1; verticalShift++) {
            for (int horizontalShift = -1; horizontalShift <= 1; horizontalShift++) {
                if((verticalShift == 0) && (horizontalShift == 0)){
                    continue;
                }
                result.add(new CoordinatesShift(verticalShift, horizontalShift));
            }
        }

        return result;

    }

    @Override
    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        boolean result = super.isSquareAvailableForMove(coordinates, board);

        if (result){
            return !board.isSquareAttackedByColor(coordinates, color.opposite());
        }
        return false;
    }
}
