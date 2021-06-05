package ru.geekbrains.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setOnClickListeners();
    }

    private void setOnClickListeners() {
        ArrayList<View> buttons = ((FrameLayout) findViewById(R.id.keyboard)).getTouchables();

        for (View button:buttons) {
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        CharSequence buttonText = ((MaterialButton) v).getText();

        TextView scoreBoard = findViewById(R.id.scoreboard);
        scoreBoard.append(buttonText);


    }

    private boolean isNumber(CharSequence buttonText) {

        return false;
    }



}