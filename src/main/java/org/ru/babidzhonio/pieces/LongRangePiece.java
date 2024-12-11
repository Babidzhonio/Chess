package org.ru.babidzhonio.pieces;

import org.ru.babidzhonio.board.Board;
import org.ru.babidzhonio.board.BoardUtils;
import org.ru.babidzhonio.Color;
import org.ru.babidzhonio.Coordinates;

import java.util.List;

public abstract class LongRangePiece extends Piece{

    public LongRangePiece(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        boolean result =  super.isSquareAvailableForMove(coordinates, board);
        if (result){
            return isSquareAvailableForAttack(coordinates, board);
        }
        else {
            return false;
        }
    }

    @Override
    protected boolean isSquareAvailableForAttack(Coordinates coordinates, Board board) {
        List<Coordinates> coordinatesBetween;
        if (this.coordinates.vertical == coordinates.vertical) {
            coordinatesBetween = BoardUtils.getVerticalCoordinatesBetween(this.coordinates, coordinates);
        } else if (this.coordinates.horizontal.equals(coordinates.horizontal)) {
            coordinatesBetween = BoardUtils.getHorizontalCoordinatesBetween(this.coordinates, coordinates);
        } else {
            coordinatesBetween = BoardUtils.getDiagonalCoordinatesBetween(this.coordinates, coordinates);
        }
        for (Coordinates c : coordinatesBetween) {
            if (!board.isSquareEmpty(c)) {
                return false;
            }
        }
        return true;
    }
}
