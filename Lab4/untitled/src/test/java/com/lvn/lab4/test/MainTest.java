package com.lvn.lab4.test;

import com.lvn.lab4.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    Main main;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        main = new Main();
    }

    @Test
    void calculateY() {
        double bx = 0.1;
        double expected = -0.0003;
        assertEquals(expected, main.calculateY(bx), 1e-4);
    }

    @Test
    void calculateY1() {
        double bx = 0.9;
        double expected = 0.8542;
        assertEquals(expected, main.calculateY(bx), 1e-4);
    }

    @Test
    void calculateY2() {
        double bx = 0.8;
        double expected = 0.703;
        assertEquals(expected, main.calculateY(bx), 1e-4);
    }

    @Test
    void calculateY3() {
        double bx = 0.3;
        double expected = -0.0093;
        assertEquals(expected, main.calculateY(bx), 1e-4);
    }

    @Test
    void calculateStepsCount() {
        double a = 0.1;
        double b = 1;
        double d = 0.001;
        double expected = 901;
        assertEquals(expected, main.calculateStepsCount(a, b, d));
    }

    @Test
    void calculateYs() {
        double a = 0.1;
        double b = 0.5;
        double d = 0.1;
        double[] expected = {-0.0003, -0.0027, -0.0093, -0.0227, 0.1989};
        assertArrayEquals(expected, main.calculateYs(a, b, d), 1e-4);
    }

    @Test
    void findMax() {
        double[] arr = {1, 2, 3, 4, 5};
        int expected = 4;
        assertEquals(expected, main.findMax(arr));
    }

    @Test
    void findMaxEmpty() {
        double[] arr = {};
        int expected = -1;
        assertEquals(expected, main.findMax(arr));
    }

    @Test
    void findMin() {
        double[] arr = {1, 2, 3, 4, 5};
        int expected = 0;
        assertEquals(expected, main.findMin(arr));
    }

    @Test
    void findMinEmpty() {
        double[] arr = {};
        int expected = -1;
        assertEquals(expected, main.findMin(arr));
    }

    @Test
    void sum() {
        double[] arr = {1, 2, 3, 4, 5};
        double expected = 15;
        assertEquals(expected, main.sum(arr), 1e-4);
    }

    @Test
    void average() {
        double[] arr = {1, 2, 3, 4, 5};
        double expected = 3;
        assertEquals(expected, main.average(arr), 1e-4);
    }
}