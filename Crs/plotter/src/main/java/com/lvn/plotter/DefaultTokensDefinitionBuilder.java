package com.lvn.plotter;

import com.lvn.plotter.mathParser.definitions.unary.*;
import com.lvn.plotter.mathParser.definitions.binary.*;
import com.lvn.plotter.mathParser.definitions.functions.*;
import com.lvn.plotter.mathParser.definitions.literalParsers.*;
import com.lvn.plotter.mathParser.parser.definitions.TokensDefinition;
import com.lvn.plotter.mathParser.parser.TokensDefiner;
public class DefaultTokensDefinitionBuilder {
    private final TokensDefinition definition;

    public DefaultTokensDefinitionBuilder() throws Exception {
        char[] opChars = {'+', '-', '*', '/', '^'};
        char[] idChars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'f', 'i', 'j', 'k', 'm', 'n', 'l',
                'o', 'p', 'r', 's', 't', 'q', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'F', 'I', 'J', 'K', 'M', 'N', 'L',
                'O', 'P', 'R', 'S', 'T', 'Q', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '_'};
        char[] digitChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'};
        char[] spaceChars = {' ', '\t', '\n'};
        char delimiter = ',';
        char openingParenthesis = '(';
        char closingParenthesis = ')';
        TokensDefiner definer = new TokensDefiner(opChars,
                idChars,
                digitChars,
                spaceChars,
                delimiter,
                openingParenthesis,
                closingParenthesis,
                new DefaultDoubleParserDefinition());
        definer.defineConstant("PI", Math.PI);
        definer.defineConstant("E", Math.E);
        definer.defineUnary("-", new UnaryMinusDefinition());
        definer.defineUnary("+", new UnaryPlusDefinition());
        definer.defineBinary("+", new SumDefinition(), false);
        definer.defineBinary("-", new DiffDefinition(), false);
        definer.defineBinary("*", new MultDefinition(), true);
        definer.defineBinary("/", new DivDefinition(), false);
        definer.defineBinary("^", new PowDefinition(), true);
        definer.defineFunction("sqrt", new SqrtDefinition());
        definer.defineFunction("abs", new AbsDefinition());
        definer.defineFunction("sin", new SinDefinition());
        definer.defineFunction("asin", new AsinDefinition());
        definer.defineFunction("cos", new CosDefinition());
        definer.defineFunction("acos", new AcosDefinition());
        definer.defineFunction("tan", new TanDefinition());
        definer.defineFunction("atan", new AtanDefinition());
        definer.defineFunction("cot", new CotDefinition());
        definer.defineFunction("acot", new AcotDefinition());
        definer.defineFunction("exp", new ExpDefinition());
        definer.defineFunction("round", new RoundDefinition());
        definer.defineFunction("floor", new FloorDefinition());
        definer.defineFunction("ceil", new CeilDefinition());
        definer.defineFunction("log", new LogDefinition());
        definition = definer;
    }

    public TokensDefinition getDefinition() {
        return definition;
    }
}
