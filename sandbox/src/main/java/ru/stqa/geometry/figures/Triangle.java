package ru.stqa.geometry.figures;

public class Triangle {

    public static void main(String[] args) {
        printTrianglePerimetr(4.0, 6.0, 8.0);
        printTriangleArea(4.0, 6.0, 8.0);
    }

    public static double sideA;
    public static double sideB;
    public static double sideC;

    public Triangle(double sideA, double sideB, double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    public static void printTrianglePerimetr(double sideA, double sideB, double sideC) {
        String text = String.format("Периметр треугольника со сторонами %f, %f и %f = %f", sideA, sideB, sideC, Perimetr(sideA, sideB, sideC));
        System.out.println(text);
    }

    public static void printTriangleArea(double sideA, double sideB, double sideC) {
        String text1 = String.format("Площадь треугольника со сторонами %f, %f и %f = %f", sideA, sideB, sideC, Area(sideA, sideB, sideC));
        System.out.println(text1);
    }

    public static double Perimetr(double sideA, double sideB, double sideC) {
        return sideA + sideB + sideC;
    }

    public static double Area(double sideA, double sideB, double sideC) {
        double pp = (sideA + sideB + sideC)/2;
        return Math.sqrt(pp * (pp - sideA) * (pp - sideB) * (pp - sideC));
    }

}