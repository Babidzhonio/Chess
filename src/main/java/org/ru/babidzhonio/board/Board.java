package org.ru.babidzhonio.board;

import org.ru.babidzhonio.Color;
import org.ru.babidzhonio.Coordinates;
import org.ru.babidzhonio.pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Board {

    List<Move> moves = new ArrayList<>();
    HashMap<Coordinates, Piece> pieces = new HashMap();
    final String startingFen;

    public Board(String startingFen) {
        this.startingFen = startingFen;
    }



    public void setPieces(Coordinates coordinates, Piece piece){
        piece.coordinates = coordinates;
        pieces.put(coordinates, piece);
    }

    public void removePiece(Coordinates coordinates){
        pieces.remove(coordinates);
    }

    public void makeMove(Move move){
        Piece piece = getPiece(move.from);
        removePiece(move.from);
        setPieces(move.to, piece);

        moves.add(move);
    }

    public boolean isSquareEmpty(Coordinates coordinates){
        return !pieces.containsKey(coordinates);
    }

    public Piece getPiece(Coordinates coordinates){
        return pieces.get(coordinates);
    }

    public void setupDefaultPiecesPosition(){
        for (Vertical vertical: Vertical.values()) {
            setPieces(new Coordinates(vertical,2), new Pawn(Color.WHITE, new Coordinates(vertical,2)));
            setPieces(new Coordinates(vertical,7), new Pawn(Color.BLACK, new Coordinates(vertical,7)));
        }

        setPieces(new Coordinates(Vertical.A, 8), new Rook(Color.BLACK, new Coordinates(Vertical.A, 8)));
        setPieces(new Coordinates(Vertical.H, 8), new Rook(Color.BLACK, new Coordinates(Vertical.H, 8)));
        setPieces(new Coordinates(Vertical.A, 1), new Rook(Color.WHITE, new Coordinates(Vertical.A, 1)));
        setPieces(new Coordinates(Vertical.H, 1), new Rook(Color.WHITE, new Coordinates(Vertical.H, 1)));

        setPieces(new Coordinates(Vertical.B, 8), new Knight(Color.BLACK, new Coordinates(Vertical.B, 8)));
        setPieces(new Coordinates(Vertical.G, 8), new Knight(Color.BLACK, new Coordinates(Vertical.G, 8)));
        setPieces(new Coordinates(Vertical.B, 1), new Knight(Color.WHITE, new Coordinates(Vertical.B, 1)));
        setPieces(new Coordinates(Vertical.G, 1), new Knight(Color.WHITE, new Coordinates(Vertical.G, 1)));

        setPieces(new Coordinates(Vertical.C, 8), new Bishop(Color.BLACK, new Coordinates(Vertical.C, 8)));
        setPieces(new Coordinates(Vertical.F, 8), new Bishop(Color.BLACK, new Coordinates(Vertical.F, 8)));
        setPieces(new Coordinates(Vertical.C, 1), new Bishop(Color.WHITE, new Coordinates(Vertical.C, 1)));
        setPieces(new Coordinates(Vertical.F, 1), new Bishop(Color.WHITE, new Coordinates(Vertical.F, 1)));

        setPieces(new Coordinates(Vertical.D, 8), new Queen(Color.BLACK, new Coordinates(Vertical.D, 8)));
        setPieces(new Coordinates(Vertical.D, 1), new Queen(Color.WHITE, new Coordinates(Vertical.D, 1)));

        setPieces(new Coordinates(Vertical.E, 8), new King(Color.BLACK, new Coordinates(Vertical.E, 8)));
        setPieces(new Coordinates(Vertical.E, 1), new King(Color.WHITE, new Coordinates(Vertical.E, 1)));
    }

    public static boolean isSquareDark(Coordinates coordinates){
        return (((coordinates.vertical.ordinal() + 1) + coordinates.horizontal) % 2) == 0; // Если равно 0, то ячейка черная, иначе белая
    }

    public boolean isSquareAttackedByColor(Coordinates coordinates, Color color) {
        List<Piece> pieces = getPiecesByColor(color);

        for (Piece piece : pieces){
            Set<Coordinates> attackedSquares =  piece.getAttackedSquares(this);

            if (attackedSquares.contains(coordinates)){
                return true;
            }
        }
        return false;
    }

    public List<Piece> getPiecesByColor(Color color){
        List<Piece> result = new ArrayList<>();

        for (Piece piece: pieces.values()){
            if (piece.color == color){
                result.add(piece);
            }
        }
        return result;
    }


}
