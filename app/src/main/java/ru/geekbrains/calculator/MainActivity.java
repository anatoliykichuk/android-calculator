package ru.geekbrains.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Calculator calculator;
    private TextView scoreboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setOnClickListeners();
    }

    private void setOnClickListeners() {
        calculator = new Calculator(getValuesById());
        scoreboard = findViewById(R.id.scoreboard);

        View.OnClickListener operandOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.operandOnClick(v.getId());
                scoreboard.setText(calculator.getExpression());
            }
        };

        int[] operandsId = getOperandId();

        for (int index = 0; index < operandsId.length; index++) {
            findViewById(operandsId[index]).setOnClickListener(operandOnClickListener);
        }

        View.OnClickListener arithmeticalOperatorOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.arithmeticalOperatorOnClick(v.getId());
                scoreboard.setText(calculator.getExpression());
            }
        };

        int[] arithmeticalOperatorsId = getArithmeticalOperatorsId();

        for (int index = 0; index < arithmeticalOperatorsId.length; index++) {
            findViewById(arithmeticalOperatorsId[index]).setOnClickListener(arithmeticalOperatorOnClickListener);
        }

        View.OnClickListener dropOperatorOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.dropExpression();
                scoreboard.setText(calculator.getExpression());
            }
        };

        findViewById(R.id.operator_drop).setOnClickListener(dropOperatorOnClickListener);
    }

    private Map<Integer, String> getValuesById() {
        Map<Integer, String> valuesById = new HashMap<>();

        valuesById.put(R.id.number_0, ((Button)findViewById(R.id.number_0)).getText().toString());
        valuesById.put(R.id.number_1, ((Button)findViewById(R.id.number_1)).getText().toString());
        valuesById.put(R.id.number_2, ((Button)findViewById(R.id.number_2)).getText().toString());
        valuesById.put(R.id.number_3, ((Button)findViewById(R.id.number_3)).getText().toString());
        valuesById.put(R.id.number_4, ((Button)findViewById(R.id.number_4)).getText().toString());
        valuesById.put(R.id.number_5, ((Button)findViewById(R.id.number_5)).getText().toString());
        valuesById.put(R.id.number_6, ((Button)findViewById(R.id.number_6)).getText().toString());
        valuesById.put(R.id.number_7, ((Button)findViewById(R.id.number_7)).getText().toString());
        valuesById.put(R.id.number_8, ((Button)findViewById(R.id.number_8)).getText().toString());
        valuesById.put(R.id.number_9, ((Button)findViewById(R.id.number_9)).getText().toString());
        valuesById.put(R.id.decimal_separator, ((Button)findViewById(R.id.decimal_separator)).getText().toString());
        valuesById.put(R.id.operator_divide, ((Button)findViewById(R.id.operator_divide)).getText().toString());
        valuesById.put(R.id.operator_multiply, ((Button)findViewById(R.id.operator_multiply)).getText().toString());
        valuesById.put(R.id.operator_minus, ((Button)findViewById(R.id.operator_minus)).getText().toString());
        valuesById.put(R.id.operator_plus, ((Button)findViewById(R.id.operator_plus)).getText().toString());
        valuesById.put(R.id.operator_percent, ((Button)findViewById(R.id.operator_percent)).getText().toString());
        valuesById.put(R.id.operator_equal, ((Button)findViewById(R.id.operator_equal)).getText().toString());
        valuesById.put(R.id.operator_change_sign, ((Button)findViewById(R.id.operator_change_sign)).getText().toString());

        return valuesById;
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
                R.id.number_9,
                R.id.decimal_separator,
        };
    }

    private int[] getArithmeticalOperatorsId() {
        return new int [] {
                R.id.operator_divide,
                R.id.operator_multiply,
                R.id.operator_minus,
                R.id.operator_plus,
                R.id.operator_percent,
                R.id.operator_equal
        };
    }
}