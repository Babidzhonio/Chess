package org.ru.babidzhonio;

import org.ru.babidzhonio.board.*;
import org.ru.babidzhonio.pieces.King;
import org.ru.babidzhonio.pieces.Piece;

import java.util.Scanner;
import java.util.Set;

public class InputCoordinates {
    private static final Scanner scanner = new Scanner(System.in);

    public static Coordinates input(){
        while (true){
            System.out.println("Введите координаты (Пример: <а3>): ");
            String input = scanner.nextLine();

            if (input.length() != 2){
                System.out.println("Неверный формат ввода! Попробуйте еще раз");
                continue;
            }
            char verticalChar = input.charAt(0);
            char horizontalChar = input.charAt(1);

            if (!Character.isLetter(verticalChar)){
                System.out.println("Неверный формат ввода! Попробуйте еще раз");
                continue;
            }
            if (!Character.isDigit(horizontalChar)){
                System.out.println("Неверный формат ввода! Попробуйте еще раз");
                continue;
            }

            int horizontal = Character.getNumericValue(horizontalChar);
            if (horizontal < 1 || horizontal > 8){
                System.out.println("Неверный формат ввода! Попробуйте еще раз");
                continue;
            }

            Vertical vertical = Vertical.fromChar(verticalChar);
            if (vertical == null){
                System.out.println("Неверный формат ввода! Попробуйте еще раз");
                continue;
            }
            return new Coordinates(vertical, horizontal);
        }
    }

    public static Coordinates inputPieceCoordinatesFromColor(Color color, Board board){
        while (true){
            System.out.println("Введите координаты фигуры для перемещения ");
            Coordinates coordinates = input();
            if (board.isSquareEmpty(coordinates)){
                System.out.println("Пустая клетка");
                continue;
            }

            Piece piece = board.getPiece(coordinates);
            if (piece.color != color){
                System.out.println("Неправильный цвет фигуры");
                continue;
            }

            Set<Coordinates> availableMoveSquare = piece.getAvailableMoveSquare(board);
            if (availableMoveSquare.isEmpty()){
                System.out.println("Фигура заблокирована");
                continue;
            }
            return coordinates;
        }
    }

    public static Coordinates inputAvailableSquare(Set<Coordinates> coordinates){
        while (true){
            System.out.println("Введите ход для выбранной фигуры: ");
            Coordinates input = input();

            if (!coordinates.contains(input)){
                System.out.println("Введенный вами ход недоступен!");
                continue;
            }
            return input;
        }
    }

    public static Move inputMove(Board board, Color color, BoardConsoleRenderer renderer){
        while (true) {
            Coordinates fromCoordinates = InputCoordinates.inputPieceCoordinatesFromColor(color, board);

            Piece piece = board.getPiece(fromCoordinates);
            renderer.render(board, piece);
            Set<Coordinates> availableMoveSquare = piece.getAvailableMoveSquare(board);
            Coordinates toCoordinates = InputCoordinates.inputAvailableSquare(availableMoveSquare);

            Move move = new Move(fromCoordinates, toCoordinates);

            if (validateIfKingInCheckAfterMove(board, color, move)) {
                System.out.println("Король под шахом! Данный ход невозможен");
                continue;
            }
            return move;
        }
    }

    private static boolean validateIfKingInCheckAfterMove(Board board, Color color, Move move) {
        Board copyBoard = (new BoardFactory()).copy(board);
        copyBoard.makeMove(move);

        Piece king = (copyBoard.getPiecesByColor(color).stream().filter(piece -> piece instanceof King).findFirst().get());

        return copyBoard.isSquareAttackedByColor(king.coordinates, color.opposite());
    }
}
