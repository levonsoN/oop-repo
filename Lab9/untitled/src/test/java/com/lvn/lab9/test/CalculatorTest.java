package com.lvn.lab9.test;

import com.lvn.lab9.Calculator;
import javafx.geometry.Point2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void avg() {
        Point2D[] arr = {
                new Point2D(0.1, 1),
                new Point2D(0.2, 2),
                new Point2D(0.3, 3),
                new Point2D(0.4, 4),
                new Point2D(0.5, 5)};
        double expected = 3;
        assertEquals(expected, calculator.avg(arr), 1e-4);
    }

    @Test
    void sum() {
        Point2D[] arr = {
                new Point2D(0.1, 1),
                new Point2D(0.2, 2),
                new Point2D(0.3, 3),
                new Point2D(0.4, 4),
                new Point2D(0.5, 5)};
        double expected = 15;
        assertEquals(expected, calculator.sum(arr), 1e-4);
    }

    @Test
    void getMin() {
        Point2D[] arr = {
                new Point2D(0.1, -0.0003),
                new Point2D(0.2, -0.0027),
                new Point2D(0.3, -0.0093),
                new Point2D(0.4, -0.0227),
                new Point2D(0.5, 0.1989)};
        Point2D expected = arr[3];
        assertEquals(expected, calculator.getMin(arr));
    }

    @Test
    void getMax() {
        Point2D[] arr = {
                new Point2D(0.1, -0.0003),
                new Point2D(0.2, -0.0027),
                new Point2D(0.3, -0.0093),
                new Point2D(0.4, -0.0227),
                new Point2D(0.5, 0.1989)};
        Point2D expected = arr[4];
        assertEquals(expected, calculator.getMax(arr));
    }

    @Test
    void tabulateValues() {
        double a = 0.1;
        double b = 0.5;
        double d = 0.1;
        Point2D[] expected = {
                new Point2D(0.1, -0.0003),
                new Point2D(0.2, -0.0027),
                new Point2D(0.3, -0.0093),
                new Point2D(0.4, -0.0227),
                new Point2D(0.5, 0.1989)};
        Point2D[] actual = calculator.tabulate(a, b, d);
        for (int i = 0; i < actual.length; i++)
            assertEquals(expected[i].getY(), actual[i].getY(), 1e-4);
    }

    @Test
    void tabulateCount() {
        assertEquals(11, calculator.tabulate(0, 1, 0.1).length);
    }

    @Test
    void calculate() {
        double bx = 0.1;
        double expected = -0.0003;
        assertEquals(expected, calculator.calculate(bx).getY(), 1e-4);
    }

    @Test
    void calculate1() {
        double bx = 0.9;
        double expected = 0.8542;
        assertEquals(expected, calculator.calculate(bx).getY(), 1e-4);
    }

    @Test
    void calculate2() {
        double bx = 0.8;
        double expected = 0.703;
        assertEquals(expected, calculator.calculate(bx).getY(), 1e-4);
    }

    @Test
    void calculate3() {
        double bx = 0.3;
        double expected = -0.0093;
        assertEquals(expected, calculator.calculate(bx).getY(), 1e-4);
    }
}