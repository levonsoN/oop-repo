package com.lvn.lab10.test;

import com.lvn.lab10.Function;

public class SinFunction implements Function {
    @Override
    public double f(double x) {
        return Math.sin(x);
    }
}
