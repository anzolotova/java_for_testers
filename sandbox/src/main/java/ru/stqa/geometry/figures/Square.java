package ru.stqa.geometry.figures;

public class Square {

    private double side;

    public Square(double side) {
        this.side = side;
    }

    public static void printSquareArea(double side) {
        String text = String.format("Площадь квадрата со стороной %f = %f", side, Area(side));
        System.out.println(text);
    }

    public static double Area(double a) {
        return a * a;
    }

    public static double perimeter(double a) {
        return 4 * a;
    }

    public double Area() {
        return this.side * this.side;
    }

    public double perimeter() {
        return 4 * this.side;
    }
}
