package com.lvn.plotter.mathParser.definitions.binary;

import com.lvn.plotter.mathParser.parser.definitions.BinaryDefinition;

public class DiffDefinition implements BinaryDefinition {
    @Override
    public double evaluate(double x, double y) {
        return x - y;
    }
}
