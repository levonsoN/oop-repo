package com.lvn.lab9;

import javafx.geometry.Point2D;

public class Calculator {
    public double avg(Point2D[] points) {
        return sum(points) / points.length;
    }

    public double sum(Point2D[] points) {
        double result = 0;
        for (int i = 0; i < points.length; i++)
            result += points[i].getY();
        return result;
    }

    public Point2D getMin(Point2D[] points) {
        return getMinOrMax(points, false);
    }

    public Point2D getMax(Point2D[] points) {
        return getMinOrMax(points, true);
    }
    public Point2D[] tabulate(double a, double b, double h) {
        int count = (int) Math.round((b - a) / h) + 1;
        Point2D[] result = new Point2D[count];
        for (int i = 0; i < count; i++)
            result[i] = calculate(a + i * h);
        return result;
    }
    public Point2D calculate(double bx) {
        return new Point2D(bx, bx <= 0.45 ? bx - Math.tan(bx) : bx + Math.log10(bx));
    }
    private Point2D getMinOrMax(Point2D[] points, boolean isMax) {
        if (points.length == 0)
            return null;
        Point2D result = points[0];
        for (int i = 1; i < points.length; i++) {
            if (isMax && result.getY() < points[i].getY())
                result = points[i];
            else if (!isMax && result.getY() > points[i].getY())
                result = points[i];
        }
        return result;
    }
}
