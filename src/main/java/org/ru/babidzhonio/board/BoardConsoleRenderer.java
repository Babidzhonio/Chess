package org.ru.babidzhonio.board;

import org.ru.babidzhonio.Color;
import org.ru.babidzhonio.Coordinates;
import org.ru.babidzhonio.pieces.Piece;

import java.util.Collections;
import java.util.Set;


public class BoardConsoleRenderer {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE_PIECE_COLOR = "\u001B[97m";
    public static final String ANSI_BLACK_PIECE_COLOR = "\u001B[30m";

    public static final String ANSI_WHITE_SQUARE_BACKGROUND = "\u001B[47m";

    public static final String ANSI_BLACK_SQUARE_BACKGROUND = "\u001B[0;100m";

    public static final String ANSI_HIGHLIGHTED_SQUARE_BACKGROUND = "\u001B[42m";


    public void render(Board board, Piece pieceToMove){

        Set<Coordinates> availableMoveSquare = Collections.emptySet();
        StringBuilder lastLine = new StringBuilder("   ");
        int indexHorizontalLabel = 0;

        if (pieceToMove != null){
            availableMoveSquare = pieceToMove.getAvailableMoveSquare(board);
        }

        for (int rank = 8; rank >= 1 ; rank--) {
            StringBuilder line = new StringBuilder(rank + " ");

            for (Vertical vertical: Vertical.values()) {
                Coordinates coordinates = new Coordinates(vertical, rank);
                boolean isHighlight = availableMoveSquare.contains(coordinates);
                if (board.isSquareEmpty(coordinates)){
                    line.append(getSpriteForEmptySquare(coordinates, isHighlight));
                }
                else {
                    line.append(getPieceSprite(board.getPiece(coordinates), isHighlight));
                }
            }

            lastLine.append(Vertical.values()[indexHorizontalLabel]).append(getLongSpaceForEmptySquare()).append(" ");
            indexHorizontalLabel++;
            line.append(ANSI_RESET);
            System.out.println(line);
        }
        System.out.println(lastLine);
    }

    public void render(Board board){
        render(board, null);
    }

    private  String colorizeSprite(String sprite, Color pieceColor, boolean isSquareDark, boolean isHighlight){
        String result = sprite;

        if (pieceColor == Color.WHITE) {
            result = ANSI_WHITE_PIECE_COLOR + result;
        } else {
            result = ANSI_BLACK_PIECE_COLOR + result;
        }
        if (isHighlight){
            result = ANSI_HIGHLIGHTED_SQUARE_BACKGROUND + result;
        } else if (isSquareDark){
            result = ANSI_BLACK_SQUARE_BACKGROUND + result;
        }
        else{
            result = ANSI_WHITE_SQUARE_BACKGROUND + result;
        }
        return result;
    }

    private String selectUnicodeSpriteForPiece(Piece piece) {
        return switch (piece.getClass().getSimpleName()) {
            case "Pawn" -> "♟";
            case "Knight" -> "♞";
            case "Bishop" -> "♝";
            case "Rook" -> "♜";
            case "Queen" -> "♛";
            case "King" -> "♚";
            default -> "";
        };
    }


    private String getSpriteForEmptySquare(Coordinates coordinates, boolean isHighlight) {
        String longSpace = getLongSpaceForEmptySquare();
        return colorizeSprite(" " + longSpace + " ", Color.WHITE, Board.isSquareDark(coordinates), isHighlight);
    }

    private String getPieceSprite(Piece piece, boolean isHighlight) {
        return colorizeSprite(" " + selectUnicodeSpriteForPiece(piece) + " ", piece.color, Board.isSquareDark(piece.coordinates), isHighlight);
    }

    private String getLongSpaceForEmptySquare() {
        return " "; // Даёт два пробела, равные ширине фигуры
    }

}
