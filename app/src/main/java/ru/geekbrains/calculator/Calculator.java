package ru.geekbrains.calculator;

import java.util.Map;

public class Calculator {
    private Map<Integer, String> valuesById;

    private double firsOperand;
    private double secondOperand;
    private double result;
    private int operator;

    StringBuilder operandBuilder;
    StringBuilder expressionBuilder;

    public Calculator(Map<Integer, String> valuesById) {
        this.valuesById = valuesById;

        this.firsOperand = Double.MIN_VALUE;
        this.secondOperand = Double.MIN_VALUE;
        this.result = Double.MIN_VALUE;
        this.operator = Integer.MIN_VALUE;

        this.operandBuilder = new StringBuilder();
        this.expressionBuilder = new StringBuilder();
    }

    public void operandOnClick(int id) {
        if (operandBuilder.length() > 9) {
            return;
        }

        if (operandBuilder.length() == 0 && (id == R.id.number_0 || id == R.id.decimal_separator)) {
            return;
        }

        String number = valuesById.get(id);

        operandBuilder.append(number);
        expressionBuilder.append(number);

        if (operator == Integer.MIN_VALUE) {
            firsOperand = Double.parseDouble(operandBuilder.toString());
        } else {
            secondOperand = Double.parseDouble(operandBuilder.toString());
        }
    }

    public void arithmeticalOperatorOnClick(int id) {
        if (firsOperand == Double.MIN_VALUE) {
            return;
        }

        if (result > Double.MIN_VALUE && id == R.id.operator_equal) {
            return;
        }

        if (id != R.id.operator_equal) {
            operator = id;
            operandBuilder.setLength(0);
        }

        shiftData();
        calculate();

        expressionBuilder.append(System.lineSeparator());
        expressionBuilder.append(valuesById.get(id));
        expressionBuilder.append(System.lineSeparator());

        if (result > Double.MIN_VALUE) {
            expressionBuilder.append(String.valueOf(result));
        }
    }

    public String getExpression() {
        return expressionBuilder.toString();
    }

    public void dropExpression() {
        firsOperand = Double.MIN_VALUE;
        secondOperand = Double.MIN_VALUE;
        result = Double.MIN_VALUE;
        operator = Integer.MIN_VALUE;
        operandBuilder.setLength(0);
        expressionBuilder.setLength(0);
    }

    private void shiftData() {
        if (result == Double.MIN_VALUE) {
            return;
        }

        firsOperand = result;
        secondOperand = Double.MIN_VALUE;
        result = Double.MIN_VALUE;

        operandBuilder.setLength(0);

        expressionBuilder.setLength(0);
        expressionBuilder.append(String.valueOf(firsOperand));
    }

    private void calculate() {
        if (firsOperand == Double.MIN_VALUE
                || secondOperand == Double.MIN_VALUE
                || operator == Integer.MIN_VALUE) {

            return;
        }

        if (operator == R.id.operator_divide) {

            if (secondOperand != 0) {
                result = firsOperand / secondOperand;
            }

        } else if (operator == R.id.operator_multiply) {
            result = firsOperand * secondOperand;

        } else if (operator == R.id.operator_minus) {
            result = firsOperand - secondOperand;

        } else if (operator == R.id.operator_plus) {
            result = firsOperand + secondOperand;

        } else if (operator == R.id.operator_percent) {
            result = secondOperand * 100 / firsOperand;

        }
    }
}
