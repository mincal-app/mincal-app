package com.mincal.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SetName extends Fragment {

    // TinyDB

    TinyDB tinydb;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SetName() {
        // Required empty public constructor
    }

    public static SetName newInstance(String param1, String param2) {
        SetName fragment = new SetName();
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

        ImageView arrowBackHome = getView().findViewById(R.id.arrow_back_home);
        EditText nameField = getView().findViewById(R.id.set_name_field);
        TextView setName = getView().findViewById(R.id.name_set);

        arrowBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle i = ActivityOptionsCompat.makeCustomAnimation(getContext(),
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                startActivity(new Intent(getContext(), WelcomeActivity.class), i);
                getActivity().finish();
            }
        });

        // Check if name field is empty, and change background drawable.

        int setNamePaddingHorizontal = setName.getPaddingLeft();
        int setNamePaddingVertical = setName.getPaddingTop();
        setName.setWidth(nameField.getWidth());
        setName.setHeight(nameField.getHeight());

        // Changing the state of the Next button if user has entered any input on the field.

        nameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setName.setBackgroundResource(R.drawable.rounded_button_disabled);
                setName.setPadding(setNamePaddingHorizontal, setNamePaddingVertical, setNamePaddingHorizontal, setNamePaddingVertical);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (nameField.getText().toString().isEmpty()) {
                    setName.setBackgroundResource(R.drawable.rounded_button_disabled);
                } else {
                    setName.setBackgroundResource(R.drawable.rounded_button);
                    setName.setPadding(0, 0, 0, 6);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (nameField.getText().toString().isEmpty()) {
                    setName.setBackgroundResource(R.drawable.rounded_button_disabled);
                } else {
                    setName.setBackgroundResource(R.drawable.rounded_button);
                    setName.setPadding(0, 0, 10, 10);
                }
            }
        });

        setName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nameField.getText().toString().isEmpty()) {

                    // Saving user name.

                    setName(nameField.getText().toString().trim());

                    // Removing current fragment and applying the next one.

                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    SetRole setRoleFragment = new SetRole();
                    SetName setNameFragment = new SetName();
                    fragmentTransaction.remove(setNameFragment);
                    fragmentTransaction.replace(R.id.get_started_container, setRoleFragment).commit();
                }
            }
        });
    }

    private void setName(String name) {
        tinydb.putString("user_name", name);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_name, container, false);
    }
}