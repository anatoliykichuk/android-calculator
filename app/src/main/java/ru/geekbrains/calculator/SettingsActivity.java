package ru.geekbrains.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.radiobutton.MaterialRadioButton;

public class SettingsActivity extends AppCompatActivity implements Keys {
    private Settings settings;
    private MaterialRadioButton themeLight;
    private MaterialRadioButton themeDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settings = getIntent().getExtras().getParcelable(SETTINGS);

        themeLight = findViewById(R.id.theme_light);
        themeDark = findViewById(R.id.theme_dark);

        initializeSettings();

        themeLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSettings(v.getId());
            }
        });

        themeDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSettings(v.getId());
            }
        });

        Button setSettingsButton = findViewById(R.id.set_settings);
        setSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setSettings = new Intent();
                setSettings.putExtra(SETTINGS, settings);
                setResult(RESULT_OK, setSettings);
                finish();
            }
        });
    }

    private void initializeSettings() {
        themeLight.setChecked(!settings.getDarkThemeOn());
        themeDark.setChecked(settings.getDarkThemeOn());
    }

    private void setSettings(int id) {
        settings.setDarkThemeOn(id == R.id.theme_dark);
    }
}