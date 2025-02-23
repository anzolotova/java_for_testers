package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculateArea() {
        var result = Triangle.Area(4.0, 6.0, 8.0);
        Assertions.assertEquals(11.61895003862225, result);
    }

    @Test
    void canCalculatePerimetr () {
        Assertions.assertEquals(18.0, Triangle.Perimetr(4.0, 6.0, 8.0));
    }
}
