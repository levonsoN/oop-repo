package com.lvn.plotter.mathParser.parser;

import com.lvn.plotter.mathParser.parser.definitions.TokensDefinition;

import java.util.ArrayList;
import java.util.Stack;

public class MathParser {
    private final TokensDefinition definition;

    public MathParser(TokensDefinition definition) {
        this.definition = definition;
    }

    public MathExpression parse(String expression) throws Exception {
        Token[] tokens = tokenize(expression);
        Stack<Token> tempStack = new Stack<>();
        ArrayList<Token> output = new ArrayList<>();
        for (Token token : tokens) {
            if (token.getTokenType() == TokenType.Literal ||
                    token.getTokenType() == TokenType.Variable ||
                    token.getTokenType() == TokenType.Constant)
                output.add(token);
            else if (token.getTokenType() == TokenType.Function ||
                    token.getTokenType() == TokenType.UnaryOperator ||
                    token.getTokenType() == TokenType.OpeningParenthesis)
                tempStack.push(token);
            else if (token.getTokenType() == TokenType.BinaryOperator) {
                int priority = definition.getBinaryPriority(token.getToken());
                while (tempStack.size() > 0) {
                    Token tmp = tempStack.pop();
                    if (tmp.getTokenType() != TokenType.OpeningParenthesis &&
                            (tmp.getTokenType() == TokenType.UnaryOperator ||
                                    tmp.getTokenType() == TokenType.Function ||
                                    definition.getBinaryPriority(tmp.getToken()) >= priority)) {
                        output.add(tmp);
                    } else {
                        tempStack.push(tmp);
                        break;
                    }
                }
                tempStack.push(token);
            } else if (token.getTokenType() == TokenType.ClosingParenthesis ||
                    token.getTokenType() == TokenType.Delimiter) {
                while (true) {
                    if (tempStack.size() == 0)
                        throw new Exception("Missing opening parenthesis.");
                    Token tmp = tempStack.pop();
                    if (tmp.getTokenType() == TokenType.OpeningParenthesis) {
                        if (token.getTokenType() == TokenType.Delimiter)
                            tempStack.push(tmp);
                        break;
                    } else output.add(tmp);
                }
            }
        }
        while (tempStack.size() > 0) {
            Token tmp = tempStack.pop();
            if (tmp.getTokenType() == TokenType.OpeningParenthesis)
                throw new Exception("Missing closing parenthesis.");
            output.add(tmp);
        }
        tokens = output.toArray(new Token[0]);
        checkArgsCount(tokens);
        return new MathExpression(tokens, definition);
    }

    private void checkArgsCount(Token[] tokens) throws Exception {
        int c = 0;
        for (Token t : tokens) {
            if (t.getTokenType() == TokenType.Literal ||
                    t.getTokenType() == TokenType.Variable ||
                    t.getTokenType() == TokenType.Constant)
                c++;
            else if (t.getTokenType() == TokenType.BinaryOperator)
                c--;
            else if (t.getTokenType() == TokenType.Function)
                c -= definition.getFunctionDefinition(t.getToken()).getArgsCount() - 1;
        }
        if (c != 1) throw new Exception("Wrong arguments count.");
    }

    private Token[] tokenize(String expression) throws Exception {
        expression = removeSpaces(expression);
        String[] strings = split(expression);
        TokenType[] tokenTypes = new TokenType[strings.length];
        for (int i = 0; i < strings.length; i++)
            if (strings[i].equals(String.valueOf(definition.getOpeningParenthesis())))
                tokenTypes[i] = TokenType.OpeningParenthesis;
            else if (strings[i].equals(String.valueOf(definition.getClosingParenthesis())))
                tokenTypes[i] = TokenType.ClosingParenthesis;
            else if (strings[i].equals(String.valueOf(definition.getDelimiter())))
                tokenTypes[i] = TokenType.Delimiter;
            else if (definition.containsOpChar(strings[i].charAt(0)))
                tokenTypes[i] = TokenType.Operator;
            else if (definition.containsIdChar(strings[i].charAt(0)))
                tokenTypes[i] = TokenType.Identifier;
            else if (definition.containsDigitChar(strings[i].charAt(0)))
                tokenTypes[i] = TokenType.Literal;

        Token[] tokens = new Token[strings.length];
        for (int i = 0; i < tokenTypes.length; i++) {
            if (i < tokenTypes.length - 1)
                if ((tokenTypes[i] == TokenType.Identifier && tokenTypes[i + 1] == TokenType.Literal) ||
                        (tokenTypes[i] == TokenType.Literal && tokenTypes[i + 1] == TokenType.Identifier))
                    throw new Exception("Identifiers and literals can't be placed together.");
            if (tokenTypes[i] == TokenType.Operator) {
                if (i == 0 || tokenTypes[i - 1] == TokenType.OpeningParenthesis ||
                        tokenTypes[i - 1] == TokenType.Delimiter)
                    tokenTypes[i] = TokenType.UnaryOperator;
                else tokenTypes[i] = TokenType.BinaryOperator;
            } else if (tokenTypes[i] == TokenType.Identifier) {
                if (i < tokenTypes.length - 1 && tokenTypes[i + 1] == TokenType.OpeningParenthesis)
                    tokenTypes[i] = TokenType.Function;
                else if (definition.isConstantDefined(strings[i]))
                    tokenTypes[i] = TokenType.Constant;
                else tokenTypes[i] = TokenType.Variable;
            }
            if (tokenTypes[i] == TokenType.Literal) {
                try {
                    tokens[i] = new Token(strings[i], tokenTypes[i], definition.getLiteralParserDefinition().parse(strings[i]));
                } catch (Exception ex) {
                    throw new Exception("Parsing literal failed: " + ex.getMessage());
                }
            } else tokens[i] = new Token(strings[i], tokenTypes[i]);
        }
        return tokens;
    }

    private String[] split(String expression) throws Exception {
        int state = 0;
        ArrayList<String> tmp = new ArrayList<>();
        boolean isAdditionNeeded;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == definition.getOpeningParenthesis() ||
                    expression.charAt(i) == definition.getClosingParenthesis() ||
                    expression.charAt(i) == definition.getDelimiter()) {
                state = 0;
                isAdditionNeeded = true;
            } else if (definition.containsOpChar(expression.charAt(i))) {
                isAdditionNeeded = state != 1;
                state = 1;
            } else if (definition.containsIdChar(expression.charAt(i))) {
                isAdditionNeeded = state != 2;
                state = 2;
            } else if (definition.containsDigitChar(expression.charAt(i))) {
                isAdditionNeeded = state != 3;
                state = 3;
            } else throw new Exception("Unknown character " + expression.charAt(i) + ".");
            if (isAdditionNeeded) tmp.add(String.valueOf(expression.charAt(i)));
            else tmp.set(tmp.size() - 1, tmp.get(tmp.size() - 1) + expression.charAt(i));
        }
        return tmp.toArray(new String[0]);
    }

    private String removeSpaces(String expression) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < expression.length(); i++)
            if (!definition.containsSpaceChar(expression.charAt(i)))
                result.append(expression.charAt(i));
        return result.toString();
    }
}
