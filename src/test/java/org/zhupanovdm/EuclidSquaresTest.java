package org.zhupanovdm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EuclidSquaresTest {

    @Test
    public void testBasic() {

        assertEquals(1, EuclidSquares.getMaxSquare(1, 1));
        assertEquals(1, EuclidSquares.getMaxSquare(1, 640));
        assertEquals(1, EuclidSquares.getMaxSquare(1680, 1));
        assertEquals(640, EuclidSquares.getMaxSquare(640, 640));
        assertEquals(80, EuclidSquares.getMaxSquare(1680, 640));
        assertEquals(80, EuclidSquares.getMaxSquare(640, 1680));

        assertThrows(IllegalArgumentException.class, () -> EuclidSquares.getMaxSquare(0, 640));
        assertThrows(IllegalArgumentException.class, () -> EuclidSquares.getMaxSquare(1680, 0));
        assertThrows(IllegalArgumentException.class, () -> EuclidSquares.getMaxSquare(0, 0));

    }

}