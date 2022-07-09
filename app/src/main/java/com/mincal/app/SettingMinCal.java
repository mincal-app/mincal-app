package com.mincal.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SettingMinCal extends Fragment {

    // Views

    ImageView magicWand;

    // TinyDB

    TinyDB tinydb;
    String userColor;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SettingMinCal() {
        // Required empty public constructor
    }

    public static SettingMinCal newInstance(String param1, String param2) {
        SettingMinCal fragment = new SettingMinCal();
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

        // Instance of SharedPreferences to store the user's name.

        tinydb = new TinyDB(getContext());
        userColor = tinydb.getString("user_color");

        // Views

        magicWand = getView().findViewById(R.id.setting_mincal_wand);

        // Set wand and text color according to user selected color.

        if (userColor == "purple") {
            magicWand.setImageResource(R.drawable.ic_wand_purple);
        } else if (userColor == "orange") {
            magicWand.setImageResource(R.drawable.ic_wand_orange);
        } else if (userColor == "red") {
            magicWand.setImageResource(R.drawable.ic_wand_red);
        } else if (userColor == "green") {
            magicWand.setImageResource(R.drawable.ic_wand_green);
        } else if (userColor == "blue") {
            magicWand.setImageResource(R.drawable.ic_wand_blue);
        } else if (userColor == "yellow") {
            magicWand.setImageResource(R.drawable.ic_wand_yellow);
        } else {
            magicWand.setImageResource(R.drawable.ic_wand);
        }

        // Add the state of "completed" for future use.

        tinydb.putBoolean("get_started_completed", true);

        // Wait during 3 seconds and redirect to the user's calculator.

        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep(3*1000);

                    // After 5 seconds redirect to another intent

                    Bundle i = ActivityOptionsCompat.makeCustomAnimation(getContext(),
                            android.R.anim.fade_in, android.R.anim.fade_out).toBundle();

                    startActivity(new Intent(getContext(), Calculator.class), i);

                    //Remove activity

                    getActivity().finish();
                } catch (Exception e) {
                    Log.e(e.toString(), e.getMessage());
                }
            }
        };
        // start thread
        background.start();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting_mincal, container, false);
    }
}