package org.ru.babidzhonio;

import org.ru.babidzhonio.board.Board;
import org.ru.babidzhonio.board.BoardConsoleRenderer;
import org.ru.babidzhonio.board.Move;
import org.ru.babidzhonio.state.CheckmateGameStateChecker;
import org.ru.babidzhonio.state.GameState;
import org.ru.babidzhonio.state.GameStateChecker;
import org.ru.babidzhonio.state.StalemateGameStateChecker;

import java.util.List;

public class Game {

    List<GameStateChecker> checkers = List.of(
            new StalemateGameStateChecker(),
            new CheckmateGameStateChecker()
    );
    private final Board board;
    private final BoardConsoleRenderer renderer = new BoardConsoleRenderer();

    public Game(Board board) {
        this.board = board;
    }

    public void gameLoop(){

        Color colorToMove = Color.WHITE;
        GameState state = determineGameState(board, colorToMove);

        while (state.equals(GameState.ONGOING)){
            renderer.render(board);

            if (colorToMove == Color.WHITE){
                System.out.println("Ход белых");
            }
            else {
                System.out.println("Ход черных");
            }

            Move move = InputCoordinates.inputMove(board, colorToMove, renderer);

            board.makeMove(move);

            colorToMove = colorToMove.opposite();

            state = determineGameState(board, colorToMove);
        }
        renderer.render(board);
        if (state == GameState.STALEMATE){
            System.out.println("Игра окончена! На доске ПАТ");
        }
        if (state == GameState.CHECKMATE_TO_WHITE_KING){
            System.out.println("Игра окончена! МАТ БЕЛОМУ КОРОЛЮ");
        }
        if (state == GameState.CHECKMATE_TO_BLACK_KING){
            System.out.println("Игра окончена! МАТ ЧЕРНОМУ КОРОЛЮ");
        }
    }

    private GameState determineGameState(Board board, Color color) {
        for (GameStateChecker checker : checkers) {
            GameState state = checker.check(board, color);

            if (state != GameState.ONGOING) {
                return state;
            }
        }

        return GameState.ONGOING;
    }
}
