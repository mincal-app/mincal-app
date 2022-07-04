package com.mincal.app;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SetPowertools extends Fragment {

    // Views

    ImageView arrowBackColor;

    ConstraintLayout geometrySet;
    ConstraintLayout statisticsSet;
    ConstraintLayout algebraSet;

    ImageView geometrySetIcon;
    ImageView statisticsSetIcon;
    ImageView algebraSetIcon;

    TextView geometrySetText;
    TextView statisticsSetText;
    TextView algebraSetText;
    TextView selectPowertools;

    // Variables

    private static ArrayList<String> userPowertools = new ArrayList<>();

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SetPowertools() {
        // Required empty public constructor
    }

    public static SetPowertools newInstance(String param1, String param2) {
        SetPowertools fragment = new SetPowertools();
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

        arrowBackColor = getView().findViewById(R.id.arrow_back_set_color);

        geometrySet = getView().findViewById(R.id.geometry_set);
        statisticsSet = getView().findViewById(R.id.statistics_percom_set);
        algebraSet = getView().findViewById(R.id.algebra_set);

        geometrySetIcon = getView().findViewById(R.id.geometry_set_icon);
        statisticsSetIcon = getView().findViewById(R.id.statistics_percom_set_icon);
        algebraSetIcon = getView().findViewById(R.id.algebra_set_icon);

        geometrySetText = getView().findViewById(R.id.geometry_set_text);
        statisticsSetText = getView().findViewById(R.id.statistics_percom_set_text);
        algebraSetText = getView().findViewById(R.id.algebra_set_text);

        selectPowertools = getView().findViewById(R.id.powertool_select);

        // Resources

        int geometryIconUnselected = R.drawable.ic_meteor;
        int statisticsIconUnselected = R.drawable.ic_cubes;
        int algebraIconUnselected = R.drawable.ic_rocket;
        int geometryIconSelected = R.drawable.ic_meteor_purple;
        int statisticsIconSelected = R.drawable.ic_cubes_purple;
        int algebraIconSelected = R.drawable.ic_rocket_purple;

        // Listeners

        arrowBackColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SetColor setColorFragment = new SetColor();
                SetPowertools setPowertoolsFragment = new SetPowertools();
                fragmentTransaction.remove(setPowertoolsFragment);
                fragmentTransaction.replace(R.id.get_started_container, setColorFragment).commit();
            }
        });

        geometrySet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePowertools(geometrySet, geometrySetText, geometrySetIcon, geometryIconSelected, geometryIconUnselected, "geometry_set");
            }
        });

        statisticsSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePowertools(statisticsSet, statisticsSetText, statisticsSetIcon, statisticsIconSelected, statisticsIconUnselected, "statistics_set");
            }
        });

        algebraSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePowertools(algebraSet, algebraSetText, algebraSetIcon, algebraIconSelected, algebraIconUnselected, "algebra_set");
            }
        });
    }

    // Methods

    public void updatePowertools(ConstraintLayout powertoolContainer, TextView powertoolTextView, ImageView powertoolIcon, int selectedIcon, int unselectedIcon, String powertoolName) {
        if (userPowertools.contains(powertoolName)) {
            userPowertools.remove(powertoolName);
            powertoolTextView.setTextColor(getResources().getColor(R.color.black));
            powertoolContainer.setBackgroundResource(R.drawable.rounded_border_field);
            powertoolIcon.setImageResource(unselectedIcon);
            powertoolContainer.setPadding(0, 0, 0, 0);
        } else {
            userPowertools.add(powertoolName);
            powertoolTextView.setTextColor(getResources().getColor(R.color.purple));
            powertoolContainer.setBackgroundResource(R.drawable.rounded_border_field_selected);
            powertoolIcon.setImageResource(selectedIcon);
            powertoolContainer.setPadding(0, 0, 10, 10);
        }

        if (userPowertools.size() != 0) {
            selectPowertools.setBackgroundResource(R.drawable.rounded_button);
            selectPowertools.setPadding(0, 0, 10, 10);
        } else if (userPowertools.size() == 0) {
            selectPowertools.setBackgroundResource(R.drawable.rounded_button_disabled);
            selectPowertools.setPadding(0, 0, 0, 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_powertools, container, false);
    }
}