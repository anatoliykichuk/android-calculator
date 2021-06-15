package ru.geekbrains.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class SettingsActivity extends AppCompatActivity {
    private Boolean darkThemeOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        darkThemeOn = false;

        ToggleButton themeSwitcher = findViewById(R.id.theme_switcher);
        themeSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                darkThemeOn = !darkThemeOn;
            }
        });

        Button setSettingsButton = findViewById(R.id.set_settings);
        setSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}