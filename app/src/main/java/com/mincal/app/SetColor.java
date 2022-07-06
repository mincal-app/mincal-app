package com.mincal.app;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SetColor extends Fragment {

    // Views

    ImageView arrowBackField;

    View orangeColor;
    View redColor;
    View purpleColor;
    View greenColor;
    View blueColor;
    View yellowColor;

    TextView colorTitle;
    TextView selectColor;

    // TinyDB

    TinyDB tinydb;

    // Text span

    SpannableString stringSpan;
    ForegroundColorSpan stringColor;

    // Variables

    private static String selectedColor = "";

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

        // Instance of SharedPreferences to store the user's name.

        tinydb = new TinyDB(getContext());

        // Views

        arrowBackField = getView().findViewById(R.id.arrow_back_set_field);

        orangeColor = getView().findViewById(R.id.orange_color);
        redColor = getView().findViewById(R.id.red_color);
        purpleColor = getView().findViewById(R.id.purple_color);
        greenColor = getView().findViewById(R.id.green_color);
        blueColor = getView().findViewById(R.id.blue_color);
        yellowColor = getView().findViewById(R.id.yellow_color);

        colorTitle = getView().findViewById(R.id.color_title);
        selectColor = getView().findViewById(R.id.color_select);

        // Setting the text span

        String colorSpan = "Which color do you\nlike?";
        stringSpan = new SpannableString(colorSpan);
        stringColor = new ForegroundColorSpan(0xFF000000);

        // Listeners

        arrowBackField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SetField setFieldFragment = new SetField();
                SetColor setColorFragment = new SetColor();
                fragmentTransaction.remove(setColorFragment);
                fragmentTransaction.replace(R.id.get_started_container, setFieldFragment).commit();
            }
        });

        orangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedColor = "orange";
                updateColors();
            }
        });

        redColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedColor = "red";
                updateColors();
            }
        });

        purpleColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedColor = "purple";
                updateColors();
            }
        });

        greenColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedColor = "green";
                updateColors();
            }
        });

        blueColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedColor = "blue";
                updateColors();
            }
        });

        yellowColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedColor = "yellow";
                updateColors();
            }
        });

        selectColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selectedColor.isEmpty()) {

                    // Saving user preferred color.

                    setColor(selectedColor);

                    // Removing current fragment and applying the next one.

                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    SetPowertools setPowertoolsFragment = new SetPowertools();
                    SetColor setColorFragment = new SetColor();
                    fragmentTransaction.remove(setColorFragment);
                    fragmentTransaction.replace(R.id.get_started_container, setPowertoolsFragment).commit();
                }
            }
        });
    }

    public void refreshRoles() {
        orangeColor.setBackgroundResource(R.drawable.orange_orb);
        redColor.setBackgroundResource(R.drawable.red_orb);
        purpleColor.setBackgroundResource(R.drawable.purple_orb);
        greenColor.setBackgroundResource(R.drawable.green_orb);
        blueColor.setBackgroundResource(R.drawable.blue_orb);
        yellowColor.setBackgroundResource(R.drawable.yellow_orb);
    }

    public void updateColors() {

        refreshRoles(); // Deselect all roles.

        if (selectedColor == "orange") {
            orangeColor.setBackgroundResource(R.drawable.orange_orb_selected);
            selectColor.setBackgroundResource(R.drawable.rounded_button_orange);
            stringColor = new ForegroundColorSpan(0xFFF18F01);
            selectColor.setPadding(0, 0, 10, 10);
        } else if (selectedColor == "red") {
            redColor.setBackgroundResource(R.drawable.red_orb_selected);
            selectColor.setBackgroundResource(R.drawable.rounded_button_red);
            stringColor = new ForegroundColorSpan(0xFFEC0B43);
            selectColor.setPadding(0, 0, 10, 10);
        } else if (selectedColor == "purple") {
            purpleColor.setBackgroundResource(R.drawable.purple_orb_selected);
            selectColor.setBackgroundResource(R.drawable.rounded_button_purple);
            stringColor = new ForegroundColorSpan(0xFF8702F5);
            selectColor.setPadding(0, 0, 10, 10);
        } else if (selectedColor == "green") {
            greenColor.setBackgroundResource(R.drawable.green_orb_selected);
            selectColor.setBackgroundResource(R.drawable.rounded_button_green);
            stringColor = new ForegroundColorSpan(0xFF04E762);
            selectColor.setPadding(0, 0, 10, 10);
        } else if (selectedColor == "blue") {
            blueColor.setBackgroundResource(R.drawable.blue_orb_selected);
            selectColor.setBackgroundResource(R.drawable.rounded_button_blue);
            stringColor = new ForegroundColorSpan(0xFF5DD9C1);
            selectColor.setPadding(0, 0, 10, 10);
        } else if (selectedColor == "yellow") {
            yellowColor.setBackgroundResource(R.drawable.yellow_orb_selected);
            selectColor.setBackgroundResource(R.drawable.rounded_button_yellow);
            stringColor = new ForegroundColorSpan(0xFFF5DD02);
            selectColor.setPadding(0, 0, 10, 10);
        }

        updateTextSpan(stringSpan, stringColor, colorTitle);
    }

    private void updateTextSpan(SpannableString span, ForegroundColorSpan color, TextView view) {

        // Typeface in order to change text font.

        Typeface face = ResourcesCompat.getFont(getContext(), R.font.montserrat_bold);
        StyleSpan montSemiBold = new StyleSpan(face.getStyle());

        // SpannableString

        span.setSpan(color, 6, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // Apply color to span
        span.setSpan(montSemiBold, 6, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // Apply font to span

        // Change TextView to Colored One

        view.setText(span);
    }

    private void setColor(String color) {
        tinydb.putString("user_color", color);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_color, container, false);
    }
}