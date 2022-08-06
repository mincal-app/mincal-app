package com.mincal.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SettingsMenu extends Fragment {

    // Views

    ImageView backToCalculator;
    ConstraintLayout settingsButton;
    ConstraintLayout myPowertoolsButton;
    ConstraintLayout achievementsButton;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SettingsMenu() {
        // Required empty public constructor
    }

    public static SettingsMenu newInstance(String param1, String param2) {
        SettingsMenu fragment = new SettingsMenu();
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

        backToCalculator = getView().findViewById(R.id.arrow_back_settings);
        settingsButton = getView().findViewById(R.id.settings_button);
        myPowertoolsButton = getView().findViewById(R.id.my_powertools_button);
        achievementsButton = getView().findViewById(R.id.achievements_button);

        // Listeners

        backToCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle i = ActivityOptionsCompat.makeCustomAnimation(getContext(),
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                startActivity(new Intent(getContext(), Calculator.class), i);
                getActivity().finish();
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SettingsMenu settingsHome = new SettingsMenu();
                Preferences preferencesFragment = new Preferences();
                fragmentTransaction.remove(settingsHome);
                fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                fragmentTransaction.replace(R.id.settings_container, preferencesFragment).commit();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings_menu, container, false);
    }
}