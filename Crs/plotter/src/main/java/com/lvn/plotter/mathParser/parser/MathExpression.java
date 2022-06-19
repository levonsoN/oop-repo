package com.lvn.plotter.mathParser.parser;

import com.lvn.plotter.mathParser.parser.definitions.FunctionDefinition;
import com.lvn.plotter.mathParser.parser.definitions.TokensDefinition;

import java.util.HashMap;
import java.util.Stack;

public class MathExpression {
    private final Token[] tokens;
    private final TokensDefinition definition;
    private final HashMap<String, Double> variables = new HashMap<>();

    public MathExpression(Token[] tokens, TokensDefinition definition) {
        this.tokens = tokens;
        this.definition = definition;
        for (Token t : tokens)
            if (t.getTokenType() == TokenType.Variable)
                variables.put(t.getToken(), 0.);
    }

    public String[] getVariables() {
        return variables.keySet().toArray(new String[0]);
    }

    public double getVariableValue(String name) throws Exception {
        Double val = variables.get(name);
        if (val == null) throw new Exception("Undefined variable name " + name + ".");
        return val;
    }

    public void setVariableValue(String name, double value) throws Exception {
        Double oldValue = variables.get(name);
        if (oldValue == null) throw new Exception("Undefined variable name " + name + ".");
        variables.replace(name, oldValue, value);
    }

    public double evaluate() throws Exception {
        Stack<Double> values = new Stack<>();
        for (Token t : tokens) {
            if (t.getTokenType() == TokenType.Literal)
                values.push(t.getLiteralValue());
            else if (t.getTokenType() == TokenType.Variable)
                values.push(variables.get(t.getToken()));
            else if (t.getTokenType() == TokenType.Constant)
                values.push(definition.getConstantValue(t.getToken()));
            else if (t.getTokenType() == TokenType.UnaryOperator)
                values.push(definition.getUnaryDefinition(t.getToken()).evaluate(values.pop()));
            else if (t.getTokenType() == TokenType.BinaryOperator) {
                double y = values.pop();
                double x = values.pop();
                values.push(definition.getBinaryDefinition(t.getToken()).evaluate(x, y));
            } else if (t.getTokenType() == TokenType.Function) {
                FunctionDefinition func = definition.getFunctionDefinition(t.getToken());
                double[] vars = new double[func.getArgsCount()];
                for (int i = 0; i < vars.length; i++)
                    vars[i] = values.pop();
                values.push(func.evaluate(vars));
            }
        }
        return values.pop();
    }
}