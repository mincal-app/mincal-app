package com.mincal.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class Calculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // Fill containers with the users' calculator.

        startCalculator();
    }

    private void startCalculator() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        CalculatorPad calculatorPad = new CalculatorPad();
        CalculatorScreen calculatorScreen = new CalculatorScreen();
        fragmentTransaction.replace(R.id.calculator_screen_container, calculatorScreen);
        fragmentTransaction.replace(R.id.calculator_pad_container, calculatorPad);
        fragmentTransaction.commit();
    }
}