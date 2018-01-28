package com.wordpress.lonelytripblog.calculatortdd;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Павел on 13.01.2018.
 */

public class Calculator {

    private static final Map<Character, Operands> CHARACTER_OPERANDS_MAP = new HashMap<>();

    static {
        CHARACTER_OPERANDS_MAP.put('+', Operands.PLUS_OPERAND);
        CHARACTER_OPERANDS_MAP.put('-', Operands.MINUS_OPERAND);
        CHARACTER_OPERANDS_MAP.put('*', Operands.MULTIPLY_OPERAND);
        CHARACTER_OPERANDS_MAP.put('/', Operands.DIVISION_OPERAND);
        CHARACTER_OPERANDS_MAP.put('(', Operands.LEFT_BRACKET);
        CHARACTER_OPERANDS_MAP.put(')', Operands.RIGHT_BRACKET);
    }

    private enum Operands {
        PLUS_OPERAND(1, '+'), MINUS_OPERAND(1, '-'), MULTIPLY_OPERAND(2, '*'), DIVISION_OPERAND(2, '/'),
        LEFT_BRACKET(0, '('), RIGHT_BRACKET(0, ')');

        final int precedence;
        final char symbol;

        Operands(final int precedence, final char symbol) {
            this.precedence = precedence;
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return String.valueOf(symbol);
        }
    }

    public String calculate(String expression) {
        String result = parseStringByShuntingYardAlgorithm(expression);
        Stack<Double> numbersStack = new Stack<>();
        String[] resultArray = result.split(" ");
        for (int i = 0; i < resultArray.length; i++) {
            char firstChar = resultArray[i].charAt(0);
            if (isOperator(firstChar)) {
                Double tmp;
                switch (firstChar) {
                    case '+':
                        tmp = numbersStack.pop();
                        numbersStack.push(numbersStack.pop() + tmp);
                        break;
                    case '-':
                        tmp = numbersStack.pop();
                        numbersStack.push(numbersStack.pop() - tmp);
                        break;
                    case '*':
                        tmp = numbersStack.pop();
                        numbersStack.push(numbersStack.pop() * tmp);
                        break;
                    case '/':
                        tmp = numbersStack.pop();
                        numbersStack.push(numbersStack.pop() / tmp);
                        break;
                }
            } else {
                numbersStack.push(Double.parseDouble(resultArray[i]));
            }
        }
        if (numbersStack.isEmpty()) {
            return "Mistake";
        }
        return DecimalFormat.getInstance().format(numbersStack.pop());
    }

    public String parseStringByShuntingYardAlgorithm(String expression) {
        StringBuilder resultString = new StringBuilder();
        Stack<Operands> operandsStack = new Stack<>();
        int i = 0;
        int length = expression.length();
        while (i < length) {
            char c = expression.charAt(i);
            int startIndexOfNumber = i;
            // if the token is a number, then push it to the output queue.
            while (Character.isDigit(c) || c == '.') {
                i++;
                if (i == length) break;
                c = expression.charAt(i);
            }
            if (i != startIndexOfNumber) {
                resultString.append(expression.substring(startIndexOfNumber, i)).append(" ");
            }

            if (isOperator(c)) {
                // TODO Dear future me! Here I'm making an assumption that all operands are left associated.
                // Refactor to handle right associated operands, such as exponents, as needed
                while (!operandsStack.empty()
                        && operandsStack.peek().precedence >= CHARACTER_OPERANDS_MAP.get(c).precedence
                        && operandsStack.peek() != Operands.LEFT_BRACKET) {
                    resultString.append(operandsStack.pop()).append(" ");
                }
                if (c == '+') {
                    operandsStack.push(Operands.PLUS_OPERAND);
                } else if (c == '-') {
                    operandsStack.push(Operands.MINUS_OPERAND);
                } else if (c == '*') {
                    operandsStack.push(Operands.MULTIPLY_OPERAND);
                } else if (c == '/') {
                    operandsStack.push(Operands.DIVISION_OPERAND);
                }
            }

            if (c == '(') {
                operandsStack.push(Operands.LEFT_BRACKET);
            }

            if (c == ')') {
                if (operandsStack.empty()) throw new ArithmeticException("Not matching bracket!");
                while (operandsStack.peek() != Operands.LEFT_BRACKET) {
                    resultString.append(operandsStack.pop()).append(' ');
                    if (operandsStack.empty())
                        throw new ArithmeticException("Not matching bracket!");
                }
                // Just pop the LEFT_BRACKET
                operandsStack.pop();
            }

            i++;
        }
        while (!operandsStack.empty()) {
            resultString.append(operandsStack.pop()).append(' ');
        }
        return resultString.toString();
    }

    private boolean isOperator(char c) {
        return CHARACTER_OPERANDS_MAP.containsKey(c) && c != '(' && c != ')';
    }


}
