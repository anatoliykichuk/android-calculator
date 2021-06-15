package ru.geekbrains.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class SettingsActivity extends AppCompatActivity implements Keys {
    private Settings settings;
    private ToggleButton themeSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ToggleButton themeSwitcher = findViewById(R.id.theme_switcher);

        initializeSettings();

        themeSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSettings();
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
        settings = getIntent().getExtras().getParcelable(SETTINGS);
        themeSwitcher.setChecked(settings.getDarkThemeOn());
    }

    private void setSettings() {
        settings.setDarkThemeOn(themeSwitcher.isChecked());
    }
}