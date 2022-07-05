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

public class SetRole extends Fragment {

    // Views

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
    TextView selectRole;

    // TinyDB

    TinyDB tinydb;

    // Variables

    private static String selectedRole = "";

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SetRole() {
        // Required empty public constructor
    }

    public static SetRole newInstance(String param1, String param2) {
        SetRole fragment = new SetRole();
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

        // Views

        ImageView arrowBackName = getView().findViewById(R.id.arrow_back_set_name);

        studentRole = getView().findViewById(R.id.student_role);
        teacherRole = getView().findViewById(R.id.teacher_role);
        scientistRole = getView().findViewById(R.id.scientist_role);
        individualRole = getView().findViewById(R.id.individual_role);

        studentIcon = getView().findViewById(R.id.student_icon);
        teacherIcon = getView().findViewById(R.id.teacher_icon);
        scientistIcon = getView().findViewById(R.id.scientist_icon);
        individualIcon = getView().findViewById(R.id.individual_icon);

        studentText = getView().findViewById(R.id.student_text);
        teacherText = getView().findViewById(R.id.teacher_text);
        scientistText = getView().findViewById(R.id.scientist_text);
        individualText = getView().findViewById(R.id.individual_text);
        selectRole = getView().findViewById(R.id.role_select);

        // Listeners

        arrowBackName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SetName setNameFragment = new SetName();
                SetRole setRoleFragment = new SetRole();
                fragmentTransaction.remove(setRoleFragment);
                fragmentTransaction.replace(R.id.get_started_container, setNameFragment).commit();
            }
        });

        studentRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedRole = "student";
                updateRoles();
            }
        });

        teacherRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedRole = "teacher";
                updateRoles();
            }
        });

        scientistRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedRole = "scientist";
                updateRoles();
            }
        });

        individualRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedRole = "individual";
                updateRoles();
            }
        });

        selectRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!selectedRole.isEmpty()) {

                    // Saving user role.

                    setRole(selectedRole);

                    // Removing current fragment and applying the next one.

                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    SetField setFieldFragment = new SetField();
                    SetRole setRoleFragment = new SetRole();
                    fragmentTransaction.remove(setRoleFragment);
                    fragmentTransaction.replace(R.id.get_started_container, setFieldFragment).commit();
                }
            }
        });
    }

    public void refreshRoles() {
        studentRole.setBackgroundResource(R.drawable.rounded_border_field);
        teacherRole.setBackgroundResource(R.drawable.rounded_border_field);
        scientistRole.setBackgroundResource(R.drawable.rounded_border_field);
        individualRole.setBackgroundResource(R.drawable.rounded_border_field);

        studentIcon.setImageResource(R.drawable.ic_user_astronaut_solid);
        teacherIcon.setImageResource(R.drawable.ic_chalkboard_user);
        scientistIcon.setImageResource(R.drawable.ic_flask);
        individualIcon.setImageResource(R.drawable.ic_user);

        studentText.setTextColor(getResources().getColor(R.color.black));
        teacherText.setTextColor(getResources().getColor(R.color.black));
        scientistText.setTextColor(getResources().getColor(R.color.black));
        individualText.setTextColor(getResources().getColor(R.color.black));

        studentRole.setPadding(0, 0, 0, 0);
        teacherRole.setPadding(0, 0, 0, 0);
        scientistRole.setPadding(0, 0, 0, 0);
        individualRole.setPadding(0, 0, 0, 0);
    }

    public void updateRoles() {

        refreshRoles(); // Deselect all roles.

        if (!selectedRole.isEmpty()) {
            selectRole.setBackgroundResource(R.drawable.rounded_button);
            selectRole.setPadding(0, 0, 10, 10);
        }

        if (selectedRole == "student") {
            studentRole.setBackgroundResource(R.drawable.rounded_border_field_selected_purple);
            studentIcon.setImageResource(R.drawable.ic_user_astronaut_purple);
            studentText.setTextColor(getResources().getColor(R.color.purple));
            studentRole.setPadding(0, 0, 10, 10);
        } else if (selectedRole == "teacher") {
            teacherRole.setBackgroundResource(R.drawable.rounded_border_field_selected_purple);
            teacherIcon.setImageResource(R.drawable.ic_chalkboard_user_purple);
            teacherText.setTextColor(getResources().getColor(R.color.purple));
            teacherRole.setPadding(0, 0, 10, 10);
        } else if (selectedRole == "scientist") {
            scientistRole.setBackgroundResource(R.drawable.rounded_border_field_selected_purple);
            scientistIcon.setImageResource(R.drawable.ic_flask_purple);
            scientistText.setTextColor(getResources().getColor(R.color.purple));
            scientistRole.setPadding(0, 0, 10, 10);
        } else if (selectedRole == "individual") {
            individualRole.setBackgroundResource(R.drawable.rounded_border_field_selected_purple);
            individualIcon.setImageResource(R.drawable.ic_user_purple);
            individualText.setTextColor(getResources().getColor(R.color.purple));
            individualRole.setPadding(0, 0, 10, 10);
        }
    }

    private void setRole(String role) {
        tinydb.putString("user_role", role);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_role, container, false);
    }
}