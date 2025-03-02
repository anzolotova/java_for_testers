package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    private Triangle t;
    public TriangleTests(){
        t = new Triangle(4.0,9.0,10.0 );
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

    @Test
    void  cannotCreateTriangleNotValidSide() {
        try {
            new Triangle(1.0, 10.0, 1.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void testEquality() {
        var t1 = new Triangle(5.0, 4.0, 3.0);
        var t2 = new Triangle(3.0, 5.0, 4.0);
        Assertions.assertEquals(t1, t2);
    }
}
