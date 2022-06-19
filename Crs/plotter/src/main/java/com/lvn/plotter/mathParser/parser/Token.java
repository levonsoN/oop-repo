package com.lvn.plotter.mathParser.parser;

public class Token {
    private final String token;
    private final Double literalValue;
    private final TokenType tokenType;

    public Token(String token, TokenType tokenType) {
        this.token = token;
        this.tokenType = tokenType;
        literalValue = null;
    }

    public Token(String token, TokenType tokenType, double literalValue) {
        this.token = token;
        this.tokenType = tokenType;
        this.literalValue = literalValue;
    }

    public String getToken() {
        return token;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public Double getLiteralValue() {
        return literalValue;
    }
}
