package org.ru.babidzhonio.board;

import org.ru.babidzhonio.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class BoardUtils {

    public static List<Coordinates> getDiagonalCoordinatesBetween(Coordinates from, Coordinates to){
        List<Coordinates> result = new ArrayList<>();

        int verticalShift = from.vertical.ordinal() < to.vertical.ordinal() ? 1 : -1;
        int horizontalShift = from.horizontal < to.horizontal ? 1 : -1;

        for (
                int verticalIndex = from.vertical.ordinal() + verticalShift,
                horizontalIndex = from.horizontal + horizontalShift;

                verticalIndex != to.vertical.ordinal() || horizontalIndex != to.horizontal;

                verticalIndex += verticalShift, horizontalIndex += horizontalShift
        ){
            result.add(new Coordinates(Vertical.values()[verticalIndex], horizontalIndex));
        }

        return result;
    }

    public static List<Coordinates> getVerticalCoordinatesBetween(Coordinates source, Coordinates target) {

        List<Coordinates> result = new ArrayList<>();
        int rankShift = source.horizontal < target.horizontal ? 1 : -1;

        for (int rank = source.horizontal + rankShift; rank != target.horizontal; rank += rankShift) {
            result.add(new Coordinates(source.vertical, rank));
        }
        return result;
    }

    public static List<Coordinates> getHorizontalCoordinatesBetween(Coordinates source, Coordinates target) {

        List<Coordinates> result = new ArrayList<>();
        int fileShift = source.vertical.ordinal() < target.vertical.ordinal() ? 1 : -1;

        for (
                int fileIndex = source.vertical.ordinal() + fileShift; fileIndex != target.vertical.ordinal();
                fileIndex += fileShift
        ) {
            result.add(new Coordinates(Vertical.values()[fileIndex], source.horizontal));
        }

        return result;
    }
}
