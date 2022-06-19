package com.lvn.lab10.test;

import com.lvn.lab10.Integrator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegratorTest {
    private Integrator linearIntegrator;
    private Integrator quadraticIntegrator;
    private Integrator sinIntegrator;

    @BeforeEach
    void setUp() {
        linearIntegrator = new Integrator(new LinearFunction(), 0, 1, 0.00001);
        quadraticIntegrator = new Integrator(new QuadraticFunction(), 0, 1, 0.00001);
        sinIntegrator = new Integrator(new SinFunction(), 0, 1, 0.00001);
    }

    @Test
    void integrate1() {
        double expected = 0.5;
        assertEquals(expected, linearIntegrator.integrate(), 1e-4);
    }

    @Test
    void integrate2() {
        double expected = 1. / 3.;
        assertEquals(expected, quadraticIntegrator.integrate(), 1e-4);
    }

    @Test
    void integrate3() {
        double expected = 0.45969;
        assertEquals(expected, sinIntegrator.integrate(), 1e-4);
    }

}