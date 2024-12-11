package org.ru.babidzhonio.board;

import org.ru.babidzhonio.Coordinates;
import org.ru.babidzhonio.pieces.PieceFactory;

public class BoardFactory {

    private final PieceFactory pieceFactory = new PieceFactory();

    public Board fromFEN(String fen){
        Board board = new Board(fen);
        String[] parts = fen.split(" ");
        String piecePosition = parts[0];
        String[] rows = piecePosition.split("/");

        for (int i = 0; i < rows.length; i++) {
            String row = rows[i];
            int horizontal = 8 - i;

            int fileIndex = 0;
            for (int j = 0; j < row.length();  j++) {
                char fenChar = row.charAt(j);

                if (Character.isDigit(fenChar)){
                    fileIndex += Character.getNumericValue(fenChar);
                }
                else {
                    Vertical vertical = Vertical.values()[fileIndex];
                    Coordinates coordinates = new Coordinates(vertical, horizontal);

                    board.setPieces(coordinates, pieceFactory.fromFenChar(fenChar, coordinates));
                    fileIndex++;
                }
            }
        }
        return board;
    }

    public Board copy(Board source){
        Board cloneBoard = fromFEN(source.startingFen);

        for (Move move : source.moves){
            cloneBoard.makeMove(move);
        }
        return cloneBoard;
    }

}
