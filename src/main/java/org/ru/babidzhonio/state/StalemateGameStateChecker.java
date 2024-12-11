package org.ru.babidzhonio.state;

import org.ru.babidzhonio.Color;
import org.ru.babidzhonio.Coordinates;
import org.ru.babidzhonio.board.Board;
import org.ru.babidzhonio.pieces.Piece;

import java.util.List;
import java.util.Set;

public class StalemateGameStateChecker extends GameStateChecker {

    @Override
    public GameState check(Board board, Color color) {
        List<Piece> pieces = board.getPiecesByColor(color);

        for (Piece piece : pieces){
            Set<Coordinates> availableMoveSquare = piece.getAvailableMoveSquare(board);

            if (availableMoveSquare.size() > 0){
                return GameState.ONGOING;
            }
        }
        return GameState.STALEMATE;
    }
}
