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

public class SettingsButton extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    // Private variables

    private static String name;
    private static int iconId;

    // TinyDB

    TinyDB tinydb;
    String userColor;

    // Views

    TextView nameView;
    ImageView iconView;

    public SettingsButton() {
        // Required empty public constructor
    }

    public static SettingsButton newInstance(String name, int iconId) {
        SettingsButton fragment = new SettingsButton();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, name);
        args.putInt(ARG_PARAM2, iconId);
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

        // Instance of TinyDB to update UI colors.

        tinydb = new TinyDB(getContext());
        userColor = tinydb.getString("user_color");

        // Views

        nameView = getView().findViewById(R.id.settings_button_name);
        iconView = getView().findViewById(R.id.settings_button_icon);

        // Update views

        nameView.setText(getArguments().getString(ARG_PARAM1));
        iconView.setImageResource(getArguments().getInt(ARG_PARAM2));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings_button, container, false);
    }
}