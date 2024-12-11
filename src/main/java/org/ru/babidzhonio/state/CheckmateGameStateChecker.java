package org.ru.babidzhonio.state;

import org.ru.babidzhonio.Color;
import org.ru.babidzhonio.Coordinates;
import org.ru.babidzhonio.board.Board;
import org.ru.babidzhonio.board.BoardFactory;
import org.ru.babidzhonio.board.Move;
import org.ru.babidzhonio.pieces.King;
import org.ru.babidzhonio.pieces.Piece;

import java.util.List;
import java.util.Set;

public class CheckmateGameStateChecker extends GameStateChecker {

    @Override
    public GameState check(Board board, Color color) {
        Piece king = (board.getPiecesByColor(color).stream().filter(piece -> piece instanceof King).findFirst().get());

        if(!board.isSquareAttackedByColor(king.coordinates, color.opposite())){
            return GameState.ONGOING;
        }

        List<Piece> pieces = board.getPiecesByColor(color);

        for (Piece piece : pieces) {
            Set<Coordinates> availableMoveSquare = piece.getAvailableMoveSquare(board);

            for (Coordinates coordinates : availableMoveSquare){
                Board cloneBoard = new BoardFactory().copy(board);
                cloneBoard.makeMove(new Move(piece.coordinates, coordinates));

                Piece cloneKing = (cloneBoard.getPiecesByColor(color).stream().filter(p -> p instanceof King).findFirst().get());

                if(!cloneBoard.isSquareAttackedByColor(cloneKing.coordinates, color.opposite())){
                    return GameState.ONGOING;
                }

            }
        }
        if (color == Color.WHITE){
            return GameState.CHECKMATE_TO_WHITE_KING;
        }
        else {
            return GameState.CHECKMATE_TO_BLACK_KING;
        }
    }

}
