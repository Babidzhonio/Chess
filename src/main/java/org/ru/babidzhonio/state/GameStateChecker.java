package org.ru.babidzhonio.state;

import org.ru.babidzhonio.Color;
import org.ru.babidzhonio.board.Board;

public abstract class GameStateChecker {
    public abstract GameState check(Board board, Color color);
}
