package com.wordpress.lonelytripblog.calculatortdd;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import dalvik.annotation.TestTarget;

/**
 * Created by Павел on 13.01.2018.
 */

public class Calculator {

    private enum Operands {
        PLUS_OPERAND, MINUS_OPERAND, MULTIPLY_OPERAND, DIVISION_OPERAND
    }

    public String calculate(String expression) {
        List<Double> numbers = new ArrayList<>();
        List<Operands> operands = new ArrayList<>();
        int i = 0;
        int length = expression.length();
        while (i < length) {
            char c = expression.charAt(i);
            int startIndexOfNumber = i;
            while (Character.isDigit(c) || c == '.') {
                i++;
                if (i == length) break;
                c = expression.charAt(i);
            }
            if (i != startIndexOfNumber) {
                numbers.add(Double.parseDouble(expression.substring(startIndexOfNumber, i)));
            }
            if (c == '+') {
                operands.add(Operands.PLUS_OPERAND);
            } else if (c == '-') {
                operands.add(Operands.MINUS_OPERAND);
            } else if (c == '*') {
                operands.add(Operands.MULTIPLY_OPERAND);
            } else if (c == '/') {
                operands.add(Operands.DIVISION_OPERAND);
            }
            i++;
        }
        Double result = numbers.get(0);
        for (i = 0; i < operands.size(); i++) {
            Operands currentOperand = operands.get(i);
            switch (currentOperand) {
                case PLUS_OPERAND:
                    result = CalculationBase.add(result, numbers.get(i + 1));
                    break;
                case MINUS_OPERAND:
                    result = CalculationBase.subtract(result, numbers.get(i + 1));
                    break;
                case MULTIPLY_OPERAND:
                    result = CalculationBase.multiply(result, numbers.get(i + 1));
                    break;
                case DIVISION_OPERAND:
                    result = CalculationBase.divide(result, numbers.get(i + 1));
                    break;
            }
        }
        return DecimalFormat.getInstance().format(result);
    }

}
