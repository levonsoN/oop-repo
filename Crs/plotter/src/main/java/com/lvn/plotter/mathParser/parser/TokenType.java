package com.lvn.plotter.mathParser.parser;

public enum TokenType {
    Literal,
    Constant,
    Variable,
    UnaryOperator,
    BinaryOperator,
    Function,

    OpeningParenthesis,
    ClosingParenthesis,
    Delimiter,
    Operator,
    Identifier
}
