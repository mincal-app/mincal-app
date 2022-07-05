package com.mincal.app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SetField extends Fragment {

    // Views

    ImageView arrowBackRole;

    TextView geometryField;
    TextView physicsField;
    TextView chemistryField;
    TextView generalMathsField;
    TextView selectField;

    // TinyDB

    TinyDB tinydb;

    // Variables

    private static ArrayList<String> userFields = new ArrayList<>();

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SetField() {
        // Required empty public constructor
    }

    public static SetField newInstance(String param1, String param2) {
        SetField fragment = new SetField();
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

        // TODO: Implement data-retrieving with the following comment:

        /** Getting data from TinyDB

        int dataint = tinyDB.getInt("clickCount");
        String datastring = tinyDB.getString("userName");

         **/

        // Views

        arrowBackRole = getView().findViewById(R.id.arrow_back_set_role);

        geometryField = getView().findViewById(R.id.geometry_field);
        physicsField = getView().findViewById(R.id.physics_field);
        chemistryField = getView().findViewById(R.id.chemistry_field);
        generalMathsField = getView().findViewById(R.id.general_math_field);
        selectField = getView().findViewById(R.id.fields_select);

        // Listeners

        arrowBackRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SetRole setRoleFragment = new SetRole();
                SetField setFieldFragment = new SetField();
                fragmentTransaction.remove(setFieldFragment);
                fragmentTransaction.replace(R.id.get_started_container, setRoleFragment).commit();
            }
        });

        geometryField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateField(geometryField, "geometry");
            }
        });

        physicsField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateField(physicsField, "physics");
            }
        });

        chemistryField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateField(chemistryField, "chemistry");
            }
        });

        generalMathsField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateField(generalMathsField, "general_maths");
            }
        });

        selectField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userFields.size() != 0) {

                    // Saving user fields.

                    setFields(userFields);

                    // Removing current fragment and applying the next one.

                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    SetField setFieldFragment = new SetField();
                    SetColor setColorFragment = new SetColor();
                    fragmentTransaction.remove(setFieldFragment);
                    fragmentTransaction.replace(R.id.get_started_container, setColorFragment).commit();
                }
            }
        });
    }

    // Methods

    public void updateField(TextView fieldView, String fieldName) {
        if (userFields.contains(fieldName)) {
            userFields.remove(fieldName);
            fieldView.setTextColor(getResources().getColor(R.color.black));
            fieldView.setBackgroundResource(R.drawable.rounded_border_field);
            fieldView.setPadding(0, 0, 0, 0);
        } else {
            userFields.add(fieldName);
            fieldView.setTextColor(getResources().getColor(R.color.purple));
            fieldView.setBackgroundResource(R.drawable.rounded_border_field_selected_purple);
            fieldView.setPadding(0, 0, 10, 10);
        }

        if (userFields.size() != 0) {
            selectField.setBackgroundResource(R.drawable.rounded_button);
            selectField.setPadding(0, 0, 10, 10);
        } else if (userFields.size() == 0) {
            selectField.setBackgroundResource(R.drawable.rounded_button_disabled);
            selectField.setPadding(0, 0, 0, 0);
        }
    }

    private void setFields(ArrayList<String> fields) {
        tinydb.putListString("user_fields", fields);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_field, container, false);
    }
}