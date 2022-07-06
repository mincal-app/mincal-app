package com.mincal.app;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
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

    TextView powertoolsTitle;
    TextView powertoolsDescription;

    // Resources

    int viewFrame;
    int buttonFrame;
    int uiColor;

    // TinyDB

    TinyDB tinydb;
    String userColor;

    // Text span

    SpannableString titleSpan;
    SpannableString descriptionSpan;
    ForegroundColorSpan stringColor;

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

        powertoolsTitle = getView().findViewById(R.id.powertools_title);
        powertoolsDescription = getView().findViewById(R.id.powertools_subtitle);

        // Setting the text span

        String titleSpanText = "Choose your\nPowerTools";
        String descriptionSpanText = "These will boost your calculator\nby offering you the tools you\nneed for your fields.";
        titleSpan = new SpannableString(titleSpanText);
        descriptionSpan = new SpannableString(descriptionSpanText);

        // Based on the user selected color, set the color variation for each icon.

        if (userColor == "purple") {
            uiColor = getResources().getColor(R.color.purple);
            viewFrame = R.drawable.rounded_border_field_selected_purple;
            buttonFrame = R.drawable.rounded_button_purple;
            stringColor = new ForegroundColorSpan(0xFF8702F5);
        } else if (userColor == "orange") {
            uiColor = getResources().getColor(R.color.orange);
            viewFrame = R.drawable.rounded_border_field_selected_orange;
            buttonFrame = R.drawable.rounded_button_orange;
            stringColor = new ForegroundColorSpan(0xFFF18F01);
        } else if (userColor == "red") {
            uiColor = getResources().getColor(R.color.red);
            viewFrame = R.drawable.rounded_border_field_selected_red;
            buttonFrame = R.drawable.rounded_button_red;
            stringColor = new ForegroundColorSpan(0xFFEC0B43);
        } else if (userColor == "green") {
            uiColor = getResources().getColor(R.color.green);
            viewFrame = R.drawable.rounded_border_field_selected_green;
            buttonFrame = R.drawable.rounded_button_green;
            stringColor = new ForegroundColorSpan(0xFF04E762);
        } else if (userColor == "blue") {
            uiColor = getResources().getColor(R.color.blue);
            viewFrame = R.drawable.rounded_border_field_selected_blue;
            buttonFrame = R.drawable.rounded_button_blue;
            stringColor = new ForegroundColorSpan(0xFF5DD9C1);
        } else if (userColor == "yellow") {
            uiColor = getResources().getColor(R.color.yellow);
            viewFrame = R.drawable.rounded_border_field_selected_yellow;
            buttonFrame = R.drawable.rounded_button_yellow;
            stringColor = new ForegroundColorSpan(0xFFF5DD02);
        } else {
            uiColor = getResources().getColor(R.color.purple);
            viewFrame = R.drawable.rounded_border_field_selected_purple;
            buttonFrame = R.drawable.rounded_button_purple;
            stringColor = new ForegroundColorSpan(0xFF000000);
        }

        // Update text span for title and description based on the user's preferred color.

        updateTextSpan(titleSpan, stringColor, powertoolsTitle, 11, 22);
        updateTextSpan(descriptionSpan, stringColor, powertoolsDescription, 11, 17);

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
                updatePowertools(geometrySet, geometrySetText, viewFrame, buttonFrame, "geometry_set");
            }
        });

        statisticsSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePowertools(statisticsSet, statisticsSetText, viewFrame, buttonFrame, "statistics_set");
            }
        });

        algebraSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePowertools(algebraSet, algebraSetText, viewFrame, buttonFrame, "algebra_set");
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

    public void updatePowertools(ConstraintLayout powertoolContainer, TextView powertoolTextView, int powertoolFrame, int buttonFrame, String powertoolName) {
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
            selectPowertools.setBackgroundResource(buttonFrame);
            selectPowertools.setPadding(0, 0, 10, 10);
        } else if (userPowertools.size() == 0) {
            selectPowertools.setBackgroundResource(R.drawable.rounded_button_disabled);
            selectPowertools.setPadding(0, 0, 0, 0);
        }
    }

    private void updateTextSpan(SpannableString span, ForegroundColorSpan color, TextView view, int spanStart, int spanEnd) {

        // Typeface in order to change text font.

        Typeface face = ResourcesCompat.getFont(getContext(), R.font.montserrat_bold);
        StyleSpan montSemiBold = new StyleSpan(face.getStyle());

        // SpannableString

        span.setSpan(color, spanStart, spanEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // Apply color to span
        span.setSpan(montSemiBold, spanStart, spanEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // Apply font to span

        // Change TextView to Colored One

        view.setText(span);
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