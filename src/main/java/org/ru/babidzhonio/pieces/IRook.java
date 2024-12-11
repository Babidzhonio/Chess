package org.ru.babidzhonio.pieces;

import java.util.HashSet;
import java.util.Set;

public interface  IRook {
    default Set<CoordinatesShift> getRookMove() {
        Set<CoordinatesShift> result = new HashSet<>();

        for (int i = -7; i <= 7; i++) {
            if (i == 0) continue;

            result.add(new CoordinatesShift(i, 0));
        }

        for (int i = -7; i <= 7; i++) {
            if (i == 0) continue;

            result.add(new CoordinatesShift(0, i));
        }
        return result;
    }
}

