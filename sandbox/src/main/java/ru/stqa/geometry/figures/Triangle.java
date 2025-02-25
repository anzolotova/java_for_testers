package ru.stqa.geometry.figures;

public record Triangle(double sideA, double sideB, double sideC) {

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

}