package ru.geekbrains.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setOnClickListeners();
    }

    private void setOnClickListeners() {
        calculator = new Calculator();

        View.OnClickListener operandOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.operandOnClick(v.getId());
            }
        };

        View.OnClickListener arithmeticalOperatorOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.arithmeticalOperatorOnClick(v.getId());
            }
        };

        int[] operandsId = getOperandId();

        for (int index = 0; index < operandsId.length; index++) {
            findViewById(operandsId[index]).setOnClickListener(operandOnClickListener);
        }

        int[] arithmeticalOperatorsId = getArithmeticalOperatorsId();

        for (int index = 0; index < arithmeticalOperatorsId.length; index++) {
            findViewById(arithmeticalOperatorsId[index]).setOnClickListener(arithmeticalOperatorOnClickListener);
        }
    }

    private int[] getOperandId() {
        return new int [] {
                R.id.number_0,
                R.id.number_1,
                R.id.number_2,
                R.id.number_3,
                R.id.number_4,
                R.id.number_5,
                R.id.number_6,
                R.id.number_7,
                R.id.number_8,
                R.id.number_9
        };
    }

    private int[] getArithmeticalOperatorsId() {
        return new int [] {
                R.id.operator_divide,
                R.id.operator_divide_modulo,
                R.id.operator_multiply,
                R.id.operator_minus,
                R.id.operator_plus
        };
    }
}