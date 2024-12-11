package org.ru.babidzhonio.pieces;

import org.ru.babidzhonio.Color;
import org.ru.babidzhonio.Coordinates;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece {
    public Knight(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMove() {
        return new HashSet<>(Arrays.asList(
           new CoordinatesShift(1, 2),
           new CoordinatesShift(2, 1),

           new CoordinatesShift(2, -1),
           new CoordinatesShift(1, -2),

           new CoordinatesShift(-2, -1),
           new CoordinatesShift(-1, -2),

           new CoordinatesShift(-2, 1),
           new CoordinatesShift(-1, 2)
        ));
    }
}
