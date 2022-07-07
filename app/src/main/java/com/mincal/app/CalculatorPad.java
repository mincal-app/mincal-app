package com.mincal.app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CalculatorPad extends Fragment {

    // Views

    // Number Pad

    TextView one;
    TextView two;
    TextView three;
    TextView four;
    TextView five;
    TextView six;
    TextView seven;
    TextView eight;
    TextView nine;
    TextView zero;
    TextView power;

    // Rounded Buttons

    TextView parentheses;
    TextView divide;
    TextView multiply;
    TextView subtract;
    TextView add;
    TextView factorial;
    TextView euler;
    TextView root;

    // Special Buttons

    ImageView back;
    TextView clean;
    TextView powertools;
    TextView result;

    // TinyDB

    TinyDB tinydb;
    String userColor;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public CalculatorPad() {
        // Required empty public constructor
    }

    public static CalculatorPad newInstance(String param1, String param2) {
        CalculatorPad fragment = new CalculatorPad();
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

        one = getView().findViewById(R.id.pad_numbers_one);
        two = getView().findViewById(R.id.pad_numbers_two);
        three = getView().findViewById(R.id.pad_numbers_three);
        four = getView().findViewById(R.id.pad_numbers_four);
        five = getView().findViewById(R.id.pad_numbers_five);
        six = getView().findViewById(R.id.pad_numbers_six);
        seven = getView().findViewById(R.id.pad_numbers_seven);
        eight = getView().findViewById(R.id.pad_numbers_eight);
        nine = getView().findViewById(R.id.pad_numbers_nine);
        zero = getView().findViewById(R.id.pad_numbers_zero);
        power = getView().findViewById(R.id.pad_numbers_power);

        // Rounded Buttons

        parentheses = getView().findViewById(R.id.pad_top_parentheses);
        divide = getView().findViewById(R.id.pad_top_divide);
        multiply = getView().findViewById(R.id.pad_top_multiply);
        subtract = getView().findViewById(R.id.pad_right_minus);
        add = getView().findViewById(R.id.pad_right_plus);
        factorial = getView().findViewById(R.id.pad_bottom_factorial);
        euler = getView().findViewById(R.id.pad_bottom_euler);
        root = getView().findViewById(R.id.pad_bottom_root);

        // Special Buttons

        back = getView().findViewById(R.id.pad_numbers_back);
        clean = getView().findViewById(R.id.pad_top_clean);
        powertools = getView().findViewById(R.id.pad_right_more_tools);
        result = getView().findViewById(R.id.pad_right_equal);

        // Elements as Array

        TextView[] numberPad = {one, two, three, four, five, six, seven, eight, nine, zero, power};
        TextView[] roundedButtonsPad = {parentheses, divide, multiply, subtract, add, factorial, euler, root, result};

        // Instance of SharedPreferences to store the user's name.

        tinydb = new TinyDB(getContext());
        userColor = tinydb.getString("user_color");

        // Modify gradient color based on user's selected color.

        for (int i = 0; i < numberPad.length; i++) {
            if (userColor == "orange") {
                numberPad[i].setTextColor(getResources().getColor(R.color.orange));
            } else if (userColor == "red") {
                numberPad[i].setTextColor(getResources().getColor(R.color.red));
            } else if (userColor == "blue") {
                numberPad[i].setTextColor(getResources().getColor(R.color.blue));
            } else if (userColor == "purple") {
                numberPad[i].setTextColor(getResources().getColor(R.color.purple));
            } else if (userColor == "green") {
                numberPad[i].setTextColor(getResources().getColor(R.color.green));
            } else if (userColor == "yellow") {
                numberPad[i].setTextColor(getResources().getColor(R.color.yellow));
            } else {
                numberPad[i].setTextColor(getResources().getColor(R.color.black));
            }
        }

        for (int i = 0; i < roundedButtonsPad.length; i++) {
            if (userColor == "orange") {
                roundedButtonsPad[i].setBackgroundResource(R.drawable.calculator_pad_rounded_orange);
            } else if (userColor == "red") {
                roundedButtonsPad[i].setBackgroundResource(R.drawable.calculator_pad_rounded_red);
            } else if (userColor == "blue") {
                roundedButtonsPad[i].setBackgroundResource(R.drawable.calculator_pad_rounded_blue);
            } else if (userColor == "purple") {
                roundedButtonsPad[i].setBackgroundResource(R.drawable.calculator_pad_rounded_purple);
            } else if (userColor == "green") {
                roundedButtonsPad[i].setBackgroundResource(R.drawable.calculator_pad_rounded_green);
            } else if (userColor == "yellow") {
                roundedButtonsPad[i].setBackgroundResource(R.drawable.calculator_pad_rounded_yellow);
            } else {
                roundedButtonsPad[i].setBackgroundResource(R.drawable.calculator_pad_rounded_purple);
            }
        }

        // Change color for special buttons

        if (userColor == "orange") {
            clean.setBackgroundResource(R.drawable.calculator_pad_rounded_right_orange);
            powertools.setBackgroundResource(R.drawable.calculator_pad_rounded_left_orange);
            back.setImageResource(R.drawable.ic_back_orange);
        } else if (userColor == "red") {
            clean.setBackgroundResource(R.drawable.calculator_pad_rounded_right_red);
            powertools.setBackgroundResource(R.drawable.calculator_pad_rounded_left_red);
            back.setImageResource(R.drawable.ic_back_red);
        } else if (userColor == "blue") {
            clean.setBackgroundResource(R.drawable.calculator_pad_rounded_right_blue);
            powertools.setBackgroundResource(R.drawable.calculator_pad_rounded_left_blue);
            back.setImageResource(R.drawable.ic_back_blue);
        } else if (userColor == "purple") {
            clean.setBackgroundResource(R.drawable.calculator_pad_rounded_right_purple);
            powertools.setBackgroundResource(R.drawable.calculator_pad_rounded_left_purple);
            back.setImageResource(R.drawable.ic_back_purple);
        } else if (userColor == "green") {
            clean.setBackgroundResource(R.drawable.calculator_pad_rounded_right_green);
            powertools.setBackgroundResource(R.drawable.calculator_pad_rounded_left_green);
            back.setImageResource(R.drawable.ic_back_green);
        } else if (userColor == "yellow") {
            clean.setBackgroundResource(R.drawable.calculator_pad_rounded_right_yellow);
            powertools.setBackgroundResource(R.drawable.calculator_pad_rounded_left_yellow);
            back.setImageResource(R.drawable.ic_back_yellow);
        } else {
            clean.setBackgroundResource(R.drawable.calculator_pad_rounded_right_purple);
            powertools.setBackgroundResource(R.drawable.calculator_pad_rounded_left_purple);
            back.setImageResource(R.drawable.ic_back_purple);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculator_pad, container, false);
    }
}