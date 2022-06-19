package com.lvn.plotter.mathParser.parser.definitions;

public interface FunctionDefinition {
    public int getArgsCount();
    public double evaluate(double... args) throws Exception;
}
