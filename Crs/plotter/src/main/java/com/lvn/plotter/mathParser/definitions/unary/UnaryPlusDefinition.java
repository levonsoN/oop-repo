package com.lvn.plotter.mathParser.definitions.unary;

import com.lvn.plotter.mathParser.parser.definitions.UnaryDefinition;

public class UnaryPlusDefinition implements UnaryDefinition {
    @Override
    public double evaluate(double x) {
        return x;
    }
}
