package ru.stqa.geometry.figures;

import java.util.Objects;

public record Triangle(double sideA, double sideB, double sideC) {

    public Triangle {
        if ((sideA < 0 || sideB < 0 || sideC < 0) ||
                (sideA + sideB < sideC || sideA + sideC < sideB || sideB + sideC < sideA)) {
            throw new IllegalArgumentException("Triangle does not exist");
        }
    }

    public static void printTriangleperimetr(Triangle trio) {
        String text = String.format("Периметр треугольника со сторонами %f, %f и %f = %f", trio.sideA, trio.sideB, trio.sideC, trio.perimetr());
        System.out.println(text);
    }

    public static void printTrianglearea(Triangle trio) {
        String text1 = String.format("Площадь треугольника со сторонами %f, %f и %f = %f", trio.sideA, trio.sideB, trio.sideC, trio.area());
        System.out.println(text1);
    }

    public double perimetr() {
        return this.sideA + this.sideB + this.sideC;
    }

    public double area() {
        double pp = (this.sideA + this.sideB + this.sideC)/2;
        return Math.sqrt(pp * (pp - this.sideA) * (pp - this.sideB) * (pp - this.sideC));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(triangle.sideA, this.sideA) == 0 && Double.compare(triangle.sideB, this.sideB) == 0 && Double.compare(triangle.sideC, this.sideC) == 0)
                || (Double.compare(triangle.sideA, this.sideB) == 0 && Double.compare(triangle.sideB, this.sideC) == 0 && Double.compare(triangle.sideC, this.sideA) == 0)
                || (Double.compare(triangle.sideA, this.sideC) == 0 && Double.compare(triangle.sideB, this.sideA) == 0 && Double.compare(triangle.sideC, this.sideB) == 0);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}