package ru.geekbrains.calculator;

import java.util.HashMap;
import java.util.Map;

public class Calculator {
    private double firsOperand;
    private double secondOperand;
    private String operator;
    private String lastAction;

    StringBuilder expression;
    Map<Integer, String> numbersId;
    Map<Integer, String> arithmeticalOperatorsId;

    public Calculator() {
        this.firsOperand = Double.MIN_VALUE;
        this.secondOperand = Double.MIN_VALUE;
        this.operator = "";
        this.lastAction = "";

        this.expression = new StringBuilder();;
        initializeNumbersId();
        initializeArithmeticalOperatorsId();
    }

    public void operandOnClick(int id) {
        if (expression.length() > 9) {
            return;
        }

        if (expression.length() == 0 && (id == R.id.number_0 || id == R.id.decimal_separator)) {
            return;
        }

        String number = numbersId.get(id);
        expression.append(number);
    }

    public void arithmeticalOperatorOnClick(int id) {

    }

    /////////////////////////////////////////////

    private void initializeNumbersId() {
        this.numbersId = new HashMap<>();
        this.numbersId.put(R.id.number_0, "0");
        this.numbersId.put(R.id.number_1, "1");
        this.numbersId.put(R.id.number_2, "2");
        this.numbersId.put(R.id.number_3, "3");
        this.numbersId.put(R.id.number_4, "4");
        this.numbersId.put(R.id.number_5, "5");
        this.numbersId.put(R.id.number_6, "6");
        this.numbersId.put(R.id.number_7, "7");
        this.numbersId.put(R.id.number_8, "8");
        this.numbersId.put(R.id.number_9, "9");
        this.numbersId.put(R.id.decimal_separator, ".");
    }

    private void initializeArithmeticalOperatorsId() {
        this.arithmeticalOperatorsId = new HashMap<>();
        this.arithmeticalOperatorsId.put(R.id.operator_divide, "/");
        this.arithmeticalOperatorsId.put(R.id.operator_multiply, "*");
        this.arithmeticalOperatorsId.put(R.id.operator_minus, "-");
        this.arithmeticalOperatorsId.put(R.id.operator_plus, "+");
        this.arithmeticalOperatorsId.put(R.id.operator_percent, "%");
        this.arithmeticalOperatorsId.put(R.id.operator_equal, "=");
    }
}
