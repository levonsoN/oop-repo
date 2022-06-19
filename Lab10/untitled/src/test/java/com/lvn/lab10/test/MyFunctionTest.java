package com.lvn.lab10.test;

import com.lvn.lab10.MyFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyFunctionTest {
    private MyFunction function;

    @BeforeEach
    void setUp() {
        function = new MyFunction();
    }

    @Test
    void f1() {
        double expected = 1;
        assertEquals(expected, function.f(Math.PI / 4), 1e-4);
    }

    @Test
    void f2() {
        double expected = 1.20945;
        assertEquals(expected, function.f(1), 1e-4);
    }

    @Test
    void f3() {
        double expected = 1.20945;
        assertEquals(expected, function.f(-1), 1e-4);
    }

}