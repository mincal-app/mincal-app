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

public class SetPowertools extends Fragment {

    // Views

    ImageView arrowBackColor;

    ConstraintLayout studentRole;
    ConstraintLayout teacherRole;
    ConstraintLayout scientistRole;
    ConstraintLayout individualRole;

    ImageView studentIcon;
    ImageView teacherIcon;
    ImageView scientistIcon;
    ImageView individualIcon;

    TextView studentText;
    TextView teacherText;
    TextView scientistText;
    TextView individualText;
    TextView selectPowertools;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_powertools, container, false);
    }
}