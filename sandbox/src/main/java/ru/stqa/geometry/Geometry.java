package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea( new Square(7.0));
        Square.printSquareArea( new Square( 5.0));
        Square.printSquareArea( new Square(3.0));

        Rectangle.printRectangleArea(3.0, 5.0);
        Rectangle.printRectangleArea(7.0, 9.0);

        var t = new Triangle(4,9, 10);
        double area_triangle = t.area();
        System.out.println(area_triangle);
        Triangle.printTriangleperimetr(t);
        Triangle.printTrianglearea(t);
    }

}
