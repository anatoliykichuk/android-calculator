package ru.geekbrains.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Calculator calculator;
    private TextView scoreboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setOnClickListeners();
    }

    private void setOnClickListeners() {
        ArrayList<View> buttons = ((TableLayout) findViewById(R.id.table)).getTouchables();

        calculator = new Calculator(getValuesById(buttons), getOperandsId(), getOperatorsId());
        scoreboard = findViewById(R.id.scoreboard);

        for (View button : buttons) {
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        calculator.calculate(v.getId());
        scoreboard.setText(calculator.getExpression());
    }

    private Map<Integer, String> getValuesById(ArrayList<View> buttons) {
        Map<Integer, String> valuesById = new HashMap<>();

        for (View button : buttons) {
               valuesById.put(button.getId(), ((MaterialButton) button).getText().toString());
        }
        return valuesById;
    }

    private int[] getOperandsId() {
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

    private int[] getOperatorsId() {
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