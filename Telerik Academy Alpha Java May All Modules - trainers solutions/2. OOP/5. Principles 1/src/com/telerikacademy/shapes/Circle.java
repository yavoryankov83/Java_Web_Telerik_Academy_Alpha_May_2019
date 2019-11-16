package com.telerikacademy.shapes;

public class Circle extends Shape {
    private Point center;
    private int radius;

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }
}
