package com.mincal.app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CalculatorPad extends Fragment {

    // Views

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

        // Elements as Array

        TextView[] numberPad = {one, two, three, four, five, six, seven, eight, nine, zero};

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculator_pad, container, false);
    }
}