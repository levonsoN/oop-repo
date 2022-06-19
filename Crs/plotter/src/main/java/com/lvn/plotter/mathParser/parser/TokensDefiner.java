package com.lvn.plotter.mathParser.parser;

import com.lvn.plotter.mathParser.parser.definitions.*;

import java.util.HashMap;

public class TokensDefiner implements TokensDefinition {
    private int priority = 0;
    private final char[] opChars;
    private final char[] idChars;
    private final char[] digitChars;
    private final char[] spaceChars;
    private final char delimiter;
    private final char openingParenthesis;
    private final char closingParenthesis;
    private final LiteralParserDefinition literalParserDefinition;
    private final HashMap<String, Double> constants = new HashMap<>();
    private final HashMap<String, UnaryDefinition> unaries = new HashMap<>();
    private final HashMap<String, BinaryPriority> binaries = new HashMap<>();
    private final HashMap<String, FunctionDefinition> functions = new HashMap<>();

    public TokensDefiner(char[] opChars,
                         char[] idChars,
                         char[] digitChars,
                         char[] spaceChars,
                         char delimiter,
                         char openingParenthesis,
                         char closingParenthesis,
                         LiteralParserDefinition literalParserDefinition) {
        this.opChars = opChars;
        this.idChars = idChars;
        this.digitChars = digitChars;
        this.spaceChars = spaceChars;
        this.delimiter = delimiter;
        this.openingParenthesis = openingParenthesis;
        this.closingParenthesis = closingParenthesis;
        this.literalParserDefinition = literalParserDefinition;
    }

    @Override
    public char[] getOpChars() {
        return opChars.clone();
    }

    @Override
    public char[] getIdChars() {
        return idChars.clone();
    }

    @Override
    public char[] getDigitChars() {
        return digitChars.clone();
    }

    @Override
    public char[] getSpaceChars() {
        return spaceChars.clone();
    }

    @Override
    public char getDelimiter() {
        return delimiter;
    }

    @Override
    public char getOpeningParenthesis() {
        return openingParenthesis;
    }

    @Override
    public char getClosingParenthesis() {
        return closingParenthesis;
    }

    @Override
    public boolean containsOpChar(char c) {
        return contains(c, opChars);
    }

    @Override
    public boolean containsIdChar(char c) {
        return contains(c, idChars);
    }

    @Override
    public boolean containsDigitChar(char c) {
        return contains(c, digitChars);
    }

    @Override
    public boolean containsSpaceChar(char c) {
        return contains(c, spaceChars);
    }

    @Override
    public LiteralParserDefinition getLiteralParserDefinition() {
        return literalParserDefinition;
    }

    public void defineConstant(String token, double value) throws Exception {
        checkToken(token, idChars);
        constants.put(token, value);
    }

    public void defineUnary(String token, UnaryDefinition definition) throws Exception {
        checkToken(token, opChars);
        unaries.put(token, definition);
    }

    public void defineBinary(String token, BinaryDefinition definition, boolean incrementPriority) throws Exception {
        checkToken(token, opChars);
        binaries.put(token, new BinaryPriority(incrementPriority ? ++priority : priority, definition));
    }

    public void defineFunction(String token, FunctionDefinition definition) throws Exception {
        checkToken(token, idChars);
        functions.put(token, definition);
    }

    @Override
    public double getConstantValue(String token) throws Exception {
        Double value = constants.get(token);
        if (value == null) throw new Exception("Constant " + token + "is undefined.");
        return value;
    }

    @Override
    public boolean isConstantDefined(String token) {
        return constants.containsKey(token);
    }

    @Override
    public UnaryDefinition getUnaryDefinition(String token) throws Exception {
        UnaryDefinition result = unaries.get(token);
        if (result == null) throw new Exception("Unknown unary operator " + token + ".");
        return result;
    }

    @Override
    public BinaryDefinition getBinaryDefinition(String token) throws Exception {
        BinaryPriority result = binaries.get(token);
        if (result == null) throw new Exception("Unknown binary operator " + token + ".");
        return result.definition;
    }

    @Override
    public FunctionDefinition getFunctionDefinition(String token) throws Exception {
        FunctionDefinition result = functions.get(token);
        if (result == null) throw new Exception("Unknown function " + token + ".");
        return result;
    }

    @Override
    public int getBinaryPriority(String token) throws Exception {
        BinaryPriority priority = binaries.get(token);
        if (priority == null) throw new Exception("Unknown binary operator " + token + ".");
        return priority.priority;
    }

    private boolean contains(char c, char[] chars) {
        for (char v : chars)
            if (v == c)
                return true;
        return false;
    }

    private void checkToken(String token, char[] chars) throws Exception {
        for (char c : token.toCharArray())
            if (!contains(c, chars))
                throw new Exception("Char " + c + " is not in corresponding chars set");
    }

    record BinaryPriority(int priority, BinaryDefinition definition) {
    }
}
