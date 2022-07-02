package com.mincal.app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SetColor extends Fragment {

    // Views

    ImageView arrowBackField;

    View orangeColor;
    View redColor;
    View purpleColor;
    View greenColor;
    View blueColor;
    View yellowColor;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SetColor() {
        // Required empty public constructor
    }

    public static SetColor newInstance(String param1, String param2) {
        SetColor fragment = new SetColor();
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

        arrowBackField = getView().findViewById(R.id.arrow_back_set_field);

        orangeColor = getView().findViewById(R.id.orange_color);
        redColor = getView().findViewById(R.id.red_color);
        purpleColor = getView().findViewById(R.id.purple_color);
        greenColor = getView().findViewById(R.id.green_color);
        blueColor = getView().findViewById(R.id.blue_color);
        yellowColor = getView().findViewById(R.id.yellow_color);

        // Listeners

        arrowBackField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_color, container, false);
    }
}