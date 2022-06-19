package com.example.untitled;

public class Integrator implements Runnable {
    private final Function function;
    private final double a;
    private final double b;
    private final double h;
    private double sum;

    public Integrator(Function function, double a, double b, double h) {
        this.function = function;
        this.a = a;
        this.b = b;
        this.h = h;
    }

    @Override
    public void run() {
        sum = integrate();
    }

    public double integrate() {
        double sum = 0;
        int count = (int) ((b - a) / h);
        for (int i = 0; i < count; i++)
            sum += h * function.f(a + i * h);
        return sum;
    }

    public double getSum() {
        return sum;
    }
}
