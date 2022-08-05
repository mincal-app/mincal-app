package com.mincal.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CalculatorScreen extends Fragment {

    // Views

    ConstraintLayout calculatorScreen;
    TextView screenResult;
    ImageView settingsButton;

    // TinyDB

    TinyDB tinydb;
    String userColor;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public CalculatorScreen() {
        // Required empty public constructor
    }

    public static CalculatorScreen newInstance(String param1, String param2) {
        CalculatorScreen fragment = new CalculatorScreen();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Views

        calculatorScreen = getView().findViewById(R.id.calculator_screen);
        screenResult = getView().findViewById(R.id.screen_result);
        settingsButton = getView().findViewById(R.id.screen_tools);

        // Instance of SharedPreferences to store the user's name.

        tinydb = new TinyDB(getContext());
        userColor = tinydb.getString("user_color");

        // Modify gradient color based on user's selected color.

        if ("purple".equals(userColor)) {
            calculatorScreen.setBackgroundResource(R.drawable.calculator_screen_gradient_purple);
        } else if ("orange".equals(userColor)) {
            calculatorScreen.setBackgroundResource(R.drawable.calculator_screen_gradient_orange);
        } else if ("red".equals(userColor)) {
            calculatorScreen.setBackgroundResource(R.drawable.calculator_screen_gradient_red);
        } else if ("green".equals(userColor)) {
            calculatorScreen.setBackgroundResource(R.drawable.calculator_screen_gradient_green);
        } else if ("blue".equals(userColor)) {
            calculatorScreen.setBackgroundResource(R.drawable.calculator_screen_gradient_blue);
        } else if ("yellow".equals(userColor)) {
            calculatorScreen.setBackgroundResource(R.drawable.calculator_screen_gradient_yellow);
        } else {
            calculatorScreen.setBackgroundResource(R.drawable.calculator_screen_gradient_purple);
        }

        // Listeners

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle i = ActivityOptionsCompat.makeCustomAnimation(getContext(),
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                startActivity(new Intent(getContext(), Settings.class), i);
                getActivity().finish();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculator_screen, container, false);
    }
}