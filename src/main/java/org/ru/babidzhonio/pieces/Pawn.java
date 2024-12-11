package org.ru.babidzhonio.pieces;

import org.ru.babidzhonio.board.Board;
import org.ru.babidzhonio.Color;
import org.ru.babidzhonio.Coordinates;
import org.ru.babidzhonio.board.BoardUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pawn extends Piece {
    public Pawn(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMove() {
        Set<CoordinatesShift> moves = new HashSet<>();

        if (color == Color.WHITE){
            moves.add(new CoordinatesShift(0, 1));
            if (coordinates.horizontal == 2){
                moves.add(new CoordinatesShift(0, 2));
            }
            moves.add(new CoordinatesShift(1, 1));
            moves.add(new CoordinatesShift(-1, 1));
        }
        else {
            moves.add(new CoordinatesShift(0, -1));
            if (coordinates.horizontal == 7){
                moves.add(new CoordinatesShift(0, -2));
            }
            moves.add(new CoordinatesShift(1, -1));
            moves.add(new CoordinatesShift(-1, -1));
        }
        return moves;
    }

    @Override
    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        if (this.coordinates.vertical == coordinates.vertical){
            int rankShift = Math.abs(this.coordinates.horizontal - coordinates.horizontal);

            if(rankShift == 2){
                List<Coordinates> between = BoardUtils.getVerticalCoordinatesBetween(this.coordinates, coordinates);
                return board.isSquareEmpty(between.getFirst()) && board.isSquareEmpty(coordinates);
            }
            else {
                return board.isSquareEmpty(coordinates);
            }
        }
        else {
             if (board.isSquareEmpty(coordinates)){
                 return false;
             }
             else {
                 return board.getPiece(coordinates).color != color;
             }
        }
    }

    @Override
    protected Set<CoordinatesShift> getPieceAttacks() {
        Set<CoordinatesShift> moves = new HashSet<>();

        if (color == Color.WHITE){
            moves.add(new CoordinatesShift(1, 1));
            moves.add(new CoordinatesShift(-1, 1));
        }
        else{
            moves.add(new CoordinatesShift(1, -1));
            moves.add(new CoordinatesShift(-1, -1));
        }

        return moves;
    }
}
