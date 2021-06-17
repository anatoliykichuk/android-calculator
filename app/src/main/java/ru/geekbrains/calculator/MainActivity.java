package ru.geekbrains.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

    private static final int CALCULATOR_THEME_CODE = 0;
    private static final int CALCULATOR_DARK_THEME_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        settings = new Settings();
        setTheme(getThemeIdFromStorage());

        setContentView(R.layout.activity_main);

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

        if (calculator != null) {
            scoreboard.setText(calculator.getExpression());
        }
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
            startActivityForResult(runSettings, SETTINGS_REQUEST_CODE);

        } else {
            calculator.calculate(v.getId());
            scoreboard.setText(calculator.getExpression());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != SETTINGS_REQUEST_CODE) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        if (resultCode == RESULT_OK) {
            settings = data.getParcelableExtra(SETTINGS);
            updateSettings();
            recreate();
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
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(THEME_USED, getThemeCodeById());
        editor.apply();
    }

    private int getThemeIdFromStorage(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);

        int themeCode = sharedPreferences.getInt(THEME_USED, CALCULATOR_THEME_CODE);
        int themeId = getThemeIdByCode(themeCode);

        settings.setDarkThemeOn(themeId == R.style.Theme_CalculatorDark);

        return themeId;
    }

    private int getThemeIdByCode(int themeCode) {
        if (themeCode == CALCULATOR_DARK_THEME_CODE) {
            return R.style.Theme_CalculatorDark;
        }
        return R.style.Theme_Calculator;
    }

    private int getThemeCodeById() {
        if (settings == null || !settings.getDarkThemeOn()) {
            return CALCULATOR_THEME_CODE;
        }
        return CALCULATOR_DARK_THEME_CODE;
    }
}