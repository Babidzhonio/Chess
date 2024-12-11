package org.ru.babidzhonio.pieces;

import org.ru.babidzhonio.board.Board;
import org.ru.babidzhonio.Color;
import org.ru.babidzhonio.Coordinates;

import java.util.HashSet;
import java.util.Set;

public abstract class Piece {
    public final Color color;
    public Coordinates coordinates;

    public Piece(Color color, Coordinates coordinates) {
        this.color = color;
        this.coordinates = coordinates;
    }

    public Set<Coordinates> getAvailableMoveSquare(Board board){
        Set<Coordinates> result = new HashSet<>();
        for (CoordinatesShift shift: getPieceMove()) {
            if (coordinates.canShift(shift)){
                Coordinates newCoordinates = coordinates.shift(shift);
                if (isSquareAvailableForMove(newCoordinates, board)){
                    result.add(newCoordinates);
                }
            }
        }
        return result;
    }

    protected boolean isSquareAvailableForMove(Coordinates coordinates, Board board) {
        return board.isSquareEmpty(coordinates) || board.getPiece(coordinates).color != color;
    }

    protected abstract Set<CoordinatesShift> getPieceMove();
    protected Set<CoordinatesShift> getPieceAttacks(){
        return getPieceMove();
    }

    public Set<Coordinates> getAttackedSquares(Board board) {
        Set<CoordinatesShift> pieceAttacks = getPieceAttacks();
        Set<Coordinates> result = new HashSet<>();

        for (CoordinatesShift pieceAttack : pieceAttacks){
            if(coordinates.canShift(pieceAttack)){
                Coordinates shiftCoordinates = coordinates.shift(pieceAttack);

                if(isSquareAvailableForAttack(shiftCoordinates, board)){
                    result.add(shiftCoordinates);
                }
            }
        }
        return result;
    }

    protected boolean isSquareAvailableForAttack(Coordinates coordinates, Board board) {
        return true;
    }
}
