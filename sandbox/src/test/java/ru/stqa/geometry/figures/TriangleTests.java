package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    private Triangle t;
    public TriangleTests(){
        t = new Triangle(4,9,10 );
    }

    @Test
    void canCalculateArea() {
        double result = t.area();
        Assertions.assertEquals(17.984368212422698, result);
    }

    @Test
    void canCalculatePerimetr () {
        Assertions.assertEquals(23.0, t.perimetr());
    }
}
