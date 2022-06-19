package com.lvn.plotter.mathParser.parser.definitions;

public interface TokensDefinition {
    public char[] getOpChars();

    public char[] getIdChars();

    public char[] getDigitChars();

    public char[] getSpaceChars();

    public char getDelimiter();

    public boolean containsOpChar(char c);

    public boolean containsIdChar(char c);

    public boolean containsDigitChar(char c);

    public boolean containsSpaceChar(char c);

    public char getOpeningParenthesis();

    public char getClosingParenthesis();

    public LiteralParserDefinition getLiteralParserDefinition();

    public double getConstantValue(String token) throws Exception;

    public boolean isConstantDefined(String token);

    public UnaryDefinition getUnaryDefinition(String token) throws Exception;

    public BinaryDefinition getBinaryDefinition(String token) throws Exception;

    public FunctionDefinition getFunctionDefinition(String token) throws Exception;

    public int getBinaryPriority(String token) throws Exception;
}
