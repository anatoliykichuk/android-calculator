package ru.geekbrains.calculator;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Map;

public class Calculator implements Parcelable {
    private Map<Integer, String> valuesById;
    private ArrayList<Integer> operandsId;
    private ArrayList<Integer> operatorsId;

    private double firsOperand;
    private StringBuilder firsOperandBuilder;

    private double secondOperand;
    private StringBuilder secondOperandBuilder;

    private int operatorId;
    private double result;

    private StringBuilder expressionBuilder;

    public static final Creator<Calculator> CREATOR = new Creator<Calculator>() {
        @Override
        public Calculator createFromParcel(Parcel in) {
            return new Calculator(in);
        }

        @Override
        public Calculator[] newArray(int size) {
            return new Calculator[size];
        }
    };

    public Calculator(Map<Integer, String> valuesById,
                      ArrayList<Integer> operandsId,
                      ArrayList<Integer> operatorsId)  {

        this.valuesById = valuesById;
        this.operandsId = operandsId;
        this.operatorsId = operatorsId;

        this.firsOperand = Double.MIN_VALUE;
        this.firsOperandBuilder = new StringBuilder();

        this.secondOperand = Double.MIN_VALUE;
        this.secondOperandBuilder = new StringBuilder();

        this.result = Double.MIN_VALUE;
        this.operatorId = Integer.MIN_VALUE;

        this.expressionBuilder = new StringBuilder();
    }

    protected Calculator(Parcel in) {
        firsOperand = in.readDouble();
        secondOperand = in.readDouble();
        operatorId = in.readInt();
        result = in.readDouble();
    }

    public void calculate(int id) {
        if (id == R.id.operator_drop) {
            dropResult();
            return;
        }

        setFirsOperand(id);
        setOperator(id);
        setSecondOperand(id);
        changeSign(id);
        setResult(id);
    }

    public String getExpression() {
        setExpressionBuilder();
        return expressionBuilder.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(firsOperand);
        dest.writeDouble(secondOperand);
        dest.writeInt(operatorId);
        dest.writeDouble(result);
    }

    private void setFirsOperand(int id) {
        if (operandsId.indexOf(id) == -1
                || operatorId > Integer.MIN_VALUE
                || id == R.id.number_0 && firsOperand == 0) {
            return;
        }

        String operand = valuesById.get(id);

        if (id == R.id.decimal_separator && firsOperandBuilder.indexOf(operand) != -1) {
            return;
        }

        if (id == R.id.decimal_separator && firsOperand == Double.MIN_VALUE) {
            firsOperandBuilder.append(valuesById.get(R.id.number_0));
        }

        firsOperandBuilder.append(operand);
        firsOperand = Double.parseDouble(firsOperandBuilder.toString());
    }

    private void setOperator(int id) {
        if (operatorsId.indexOf(id) == -1
                || firsOperand == Double.MIN_VALUE
                || operatorId > Integer.MIN_VALUE && result == Double.MIN_VALUE) {
            return;
        }

        if (result > Double.MIN_VALUE || result == 0) {
            shiftData();
        }

        operatorId = id;
    }

    private void setSecondOperand(int id) {
        if (operandsId.indexOf(id) == -1
                || operatorId == Integer.MIN_VALUE
                || result > Double.MIN_VALUE
                || id == R.id.number_0 && secondOperand == 0) {
            return;
        }

        String operand = valuesById.get(id);

        if (id == R.id.decimal_separator && secondOperandBuilder.indexOf(operand) != -1) {
            return;
        }

        if (id == R.id.decimal_separator && secondOperand == Double.MIN_VALUE) {
            secondOperandBuilder.append(valuesById.get(R.id.number_0));
        }

        secondOperandBuilder.append(operand);
        secondOperand = Double.parseDouble(secondOperandBuilder.toString());
    }

    private void changeSign(int id) {
        if (id != R.id.operator_change_sign) {
            return;
        }

        if (operatorId == Integer.MIN_VALUE && firsOperand > Double.MIN_VALUE) {
            firsOperand *= -1;
            firsOperandBuilder.setLength(0);
            firsOperandBuilder.append(String.valueOf(firsOperand));

        } else if (operatorId > Integer.MIN_VALUE && secondOperand > Double.MIN_VALUE) {
            secondOperand *= -1;
            secondOperandBuilder.setLength(0);
            secondOperandBuilder.append(String.valueOf(secondOperand));
        }
    }

    private void setResult(int id) {
        if (id != R.id.operator_equal
                || firsOperand == Double.MIN_VALUE
                || secondOperand == Double.MIN_VALUE
                || operatorId == Integer.MIN_VALUE) {
            return;
        }

        if (operatorId == R.id.operator_divide && secondOperand != 0) {
            result = firsOperand / secondOperand;

        } else if (operatorId == R.id.operator_multiply) {
            result = firsOperand * secondOperand;

        } else if (operatorId == R.id.operator_minus) {
            result = firsOperand - secondOperand;

        } else if (operatorId == R.id.operator_plus) {
            result = firsOperand + secondOperand;

        } else if (operatorId == R.id.operator_percent) {
            result = secondOperand * 100 / firsOperand;
        }
    }

    private void dropResult() {
        firsOperand = Double.MIN_VALUE;
        firsOperandBuilder.setLength(0);

        secondOperand = Double.MIN_VALUE;
        secondOperandBuilder.setLength(0);

        operatorId = Integer.MIN_VALUE;
        result = Double.MIN_VALUE;
    }

    private void shiftData() {
        if (result == Double.MIN_VALUE) {
            return;
        }

        firsOperand = result;
        firsOperandBuilder.setLength(0);
        firsOperandBuilder.append(String.valueOf(result));

        secondOperand = Double.MIN_VALUE;
        secondOperandBuilder.setLength(0);

        operatorId = Integer.MIN_VALUE;
        result = Double.MIN_VALUE;
    }

    private void setExpressionBuilder() {
        expressionBuilder.setLength(0);
        expressionBuilder.append(firsOperandBuilder.toString());

        if (operatorId > Integer.MIN_VALUE) {
            expressionBuilder.append(System.lineSeparator());
            expressionBuilder.append(valuesById.get(operatorId));
        }

        if (secondOperandBuilder.length() > 0) {
            expressionBuilder.append(System.lineSeparator());
            expressionBuilder.append(secondOperandBuilder.toString());
        }

        if (result > Double.MIN_VALUE || result == 0) {
            expressionBuilder.append(System.lineSeparator());
            expressionBuilder.append(valuesById.get(R.id.operator_equal));
            expressionBuilder.append(System.lineSeparator());
            expressionBuilder.append(String.valueOf(result));
        }
    }
}
