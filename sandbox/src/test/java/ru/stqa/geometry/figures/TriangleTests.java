package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculateArea() {
        var testTriangle = new Triangle(4, 9, 10);
        double result = testTriangle.area();
        Assertions.assertEquals(17.984368212422698, result);
    }

    @Test
    void canCalculatePerimetr () {
        Assertions.assertEquals(23.0, new Triangle(4, 9, 10).perimetr());
    }
}
