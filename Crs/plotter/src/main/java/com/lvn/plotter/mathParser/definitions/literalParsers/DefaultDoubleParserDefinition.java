package com.lvn.plotter.mathParser.definitions.literalParsers;

import com.lvn.plotter.mathParser.parser.definitions.LiteralParserDefinition;

public class DefaultDoubleParserDefinition implements LiteralParserDefinition {
    @Override
    public double parse(String str) {
        return Double.parseDouble(str);
    }
}
