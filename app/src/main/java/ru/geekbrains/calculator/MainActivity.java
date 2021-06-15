package ru.geekbrains.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Keys {
    private Settings settings;
    private Calculator calculator;
    private TextView scoreboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = new Settings();

        setOnClickListeners();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putParcelable(Keys.CALCULATOR, calculator);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculator = savedInstanceState.getParcelable(Keys.CALCULATOR);
        scoreboard.setText(calculator.getExpression());
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
        if (v.getId() == R.id.settings) {
            Intent runSettings = new Intent(MainActivity.this, SettingsActivity.class);
            runSettings.putExtra(SETTINGS, settings);
            startActivityForResult(runSettings, REQUEST_CODE_SETTINGS);

        } else {
            calculator.calculate(v.getId());
            scoreboard.setText(calculator.getExpression());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE_SETTINGS) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        if (resultCode == RESULT_OK) {
            settings = data.getParcelableExtra(SETTINGS);
            updateSettings();
        }
    }

    private Map<Integer, String> getValuesById(ArrayList<View> buttons) {
        Map<Integer, String> valuesById = new HashMap<>();

        for (View button : buttons) {
               valuesById.put(button.getId(), ((MaterialButton) button).getText().toString());
        }
        return valuesById;
    }

    private ArrayList<Integer> getOperandsId() {
        ArrayList<Integer> operandsId = new ArrayList<>();
        operandsId.add(R.id.number_0);
        operandsId.add(R.id.number_1);
        operandsId.add(R.id.number_2);
        operandsId.add(R.id.number_3);
        operandsId.add(R.id.number_4);
        operandsId.add(R.id.number_5);
        operandsId.add(R.id.number_6);
        operandsId.add(R.id.number_7);
        operandsId.add(R.id.number_8);
        operandsId.add(R.id.number_9);
        operandsId.add(R.id.decimal_separator);

        return operandsId;
    }

    private ArrayList<Integer> getOperatorsId() {
        ArrayList<Integer> operatorsId = new ArrayList<>();
        operatorsId.add(R.id.operator_divide);
        operatorsId.add(R.id.operator_multiply);
        operatorsId.add(R.id.operator_minus);
        operatorsId.add(R.id.operator_plus);
        operatorsId.add(R.id.operator_percent);

        return operatorsId;
    }

    private void updateSettings() {
        // TODO
    }
}