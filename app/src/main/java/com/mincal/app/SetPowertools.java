package com.mincal.app;

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

    TextView geometrySetText;
    TextView statisticsSetText;
    TextView algebraSetText;
    TextView selectPowertools;

    // Resources

    int viewFrame;
    int uiColor;

    // TinyDB

    TinyDB tinydb;
    String userColor;

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

        // Instance of SharedPreferences to store the user's name.

        tinydb = new TinyDB(getContext());
        userColor = tinydb.getString("user_color");

        // Views

        arrowBackColor = getView().findViewById(R.id.arrow_back_set_color);

        geometrySet = getView().findViewById(R.id.geometry_set);
        statisticsSet = getView().findViewById(R.id.statistics_percom_set);
        algebraSet = getView().findViewById(R.id.algebra_set);

        geometrySetText = getView().findViewById(R.id.geometry_set_text);
        statisticsSetText = getView().findViewById(R.id.statistics_percom_set_text);
        algebraSetText = getView().findViewById(R.id.algebra_set_text);

        selectPowertools = getView().findViewById(R.id.powertool_select);

        // Based on the user selected color, set the color variation for each icon.

        if (userColor == "purple") {
            uiColor = getResources().getColor(R.color.purple);
            viewFrame = R.drawable.rounded_border_field_selected_purple;
        } else if (userColor == "orange") {
            uiColor = getResources().getColor(R.color.orange);
            viewFrame = R.drawable.rounded_border_field_selected_orange;
        } else if (userColor == "red") {
            uiColor = getResources().getColor(R.color.red);
            viewFrame = R.drawable.rounded_border_field_selected_red;
        } else if (userColor == "green") {
            uiColor = getResources().getColor(R.color.green);
            viewFrame = R.drawable.rounded_border_field_selected_green;
        } else if (userColor == "blue") {
            uiColor = getResources().getColor(R.color.blue);
            viewFrame = R.drawable.rounded_border_field_selected_blue;
        } else if (userColor == "yellow") {
            uiColor = getResources().getColor(R.color.yellow);
            viewFrame = R.drawable.rounded_border_field_selected_yellow;
        } else {
            uiColor = getResources().getColor(R.color.purple);
            viewFrame = R.drawable.rounded_border_field_selected_purple;
        }

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
                updatePowertools(geometrySet, geometrySetText, viewFrame, "geometry_set");
            }
        });

        statisticsSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePowertools(statisticsSet, statisticsSetText, viewFrame, "statistics_set");
            }
        });

        algebraSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePowertools(algebraSet, algebraSetText, viewFrame, "algebra_set");
            }
        });

        selectPowertools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!userPowertools.isEmpty()) {

                    // Saving the user's powertools.

                    setPowertools(userPowertools);

                    // Removing current fragment and applying the next one.

                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    SetPowertools setPowertoolsFragment = new SetPowertools();
                    SettingMinCal buildingMincalFragment = new SettingMinCal();
                    fragmentTransaction.remove(setPowertoolsFragment);
                    fragmentTransaction.replace(R.id.get_started_container, buildingMincalFragment).commit();
                }
            }
        });
    }

    // Methods

    public void updatePowertools(ConstraintLayout powertoolContainer, TextView powertoolTextView, int powertoolFrame, String powertoolName) {
        if (userPowertools.contains(powertoolName)) {
            userPowertools.remove(powertoolName);
            powertoolTextView.setTextColor(getResources().getColor(R.color.black));
            powertoolContainer.setBackgroundResource(R.drawable.rounded_border_field);
            powertoolContainer.setPadding(0, 0, 0, 0);
        } else {
            userPowertools.add(powertoolName);
            powertoolContainer.setBackgroundResource(powertoolFrame);
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

    private void setPowertools(ArrayList<String> powertools) {
        tinydb.putListString("user_powertools", powertools);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_powertools, container, false);
    }
}