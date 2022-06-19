package com.lvn.plotter.mathParser.definitions.functions;

import com.lvn.plotter.mathParser.parser.definitions.FunctionDefinition;

public class FloorDefinition implements FunctionDefinition {
    @Override
    public int getArgsCount() {
        return 1;
    }

    @Override
    public double evaluate(double... args) throws Exception {
        if (args.length != getArgsCount())
            throw new Exception("Invalid arguments count.");
        return Math.floor(args[0]);
    }
}
