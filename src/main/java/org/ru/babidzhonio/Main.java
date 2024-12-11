
package org.ru.babidzhonio;

import org.ru.babidzhonio.board.Board;
import org.ru.babidzhonio.board.BoardFactory;

public class Main {
    public static void main(String[] args) {

        BoardFactory boardFactory = new BoardFactory();
        Board board = boardFactory.fromFEN("rnb1kbnr/pppp1ppp/4p3/8/6Pq/5P2/PPPPP2P/RNBQKBNR w KQkq - 0 1"); //   rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1 (Дефолт расстановка)

        Game game = new Game(board);
        game.gameLoop();
    }
}
