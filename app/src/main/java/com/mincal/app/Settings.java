package com.mincal.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Settings extends AppCompatActivity {

    View backToCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Views

        backToCalculator = findViewById(R.id.arrow_back_settings);

        // Listeners

        backToCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle i = ActivityOptionsCompat.makeCustomAnimation(getBaseContext(),
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                startActivity(new Intent(getBaseContext(), Calculator.class), i);
                finish();
            }
        });

        // Fragments

        setButtons();
    }

    private void setButtons() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        SettingsButton settingsButton = SettingsButton.newInstance("Settings", R.drawable.ic_pencil);
        SettingsButton powertoolsButton = SettingsButton.newInstance("My Powertools", R.drawable.ic_hat_wizard);
        SettingsButton badgesButton = SettingsButton.newInstance("Achievements", R.drawable.ic_badge);
        fragmentTransaction.replace(R.id.settings_first_row, settingsButton, "settingsTag");
        fragmentTransaction.replace(R.id.settings_second_row, powertoolsButton, "powertoolsTag");
        fragmentTransaction.replace(R.id.settings_third_row, badgesButton, "achievementsTag");
        fragmentTransaction.commit();
    }
}