package com.lvn.lab10;

public class MyFunction implements Function {
    @Override
    public double f(double x) {
        return 1 / Math.pow(Math.sin(2 * x), 2);
    }
}
