package com.mincal.app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.google.common.primitives.Ints;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class PowertoolsMenu extends Fragment {

    // TinyDB

    TinyDB tinydb;
    ArrayList<String> userPowertools;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public PowertoolsMenu() {
        // Required empty public constructor
    }

    public static PowertoolsMenu newInstance(String param1, String param2) {
        PowertoolsMenu fragment = new PowertoolsMenu();
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
        userPowertools = tinydb.getListString("user_powertools");

        // Getting icons, functions, and ids.

        PowerIcons powerIcons = new PowerIcons(userPowertools);
        HashMap<String, ArrayList<Integer>> powertoolsMap = powerIcons.getPowertools();

        /* Debug Logs */

        Log.d("PowerIcons", "Original Drawables contains: " + powertoolsMap.values());
        Log.d("PowerIcons", "TinyDB contains: " + userPowertools);
        Log.d("PowerIcons", "HashMap contains: " + powertoolsMap);

        // TODO: Look for a way to implement an ArrayList into the adapter as rows.

        ArrayList<Integer> powerDrawables = new ArrayList<>();

        for (String toolKey : powertoolsMap.keySet()) {
            for (int i = 0; i < powertoolsMap.get(toolKey).size(); i++) {
                powerDrawables.add(powertoolsMap.get(toolKey).get(i));
            }
        }

        Log.d("PowerIcons", "Drawables contains: " + powerDrawables);

        // Views

        GridView powertoolsGrid = (GridView) getView().findViewById(R.id.powertools_grid);
        TextView hidePowertools = getView().findViewById(R.id.hide_powertools);

        // Choose icons and PowerTools based on user preferences

        powertoolsGrid.setAdapter(new PowertoolsMenuAdapter(getContext(), powerDrawables));

        // Hide PowerTools Menu on Close button tap.

        // Listeners

        hidePowertools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction powerMenuTransaction = fragmentManager.beginTransaction();
                FragmentTransaction blurTransaction = fragmentManager.beginTransaction();

                powerMenuTransaction.setCustomAnimations(R.anim.swipe_out_left, R.anim.dummy_anim_swipe_left, R.anim.dummy_anim_swipe_left, R.anim.swipe_out_left);
                blurTransaction.setCustomAnimations(R.anim.fade_out, R.anim.dummy_anim_swipe_left, R.anim.dummy_anim_swipe_left, R.anim.fade_out);

                Fragment powertoolsMenu = getActivity().getSupportFragmentManager().findFragmentByTag("blur_powertools_menu");
                Fragment powertoolsBlur = getActivity().getSupportFragmentManager().findFragmentByTag("powertools_menu");
                if(powertoolsMenu!=null) powerMenuTransaction.remove(powertoolsMenu).commit();
                if(powertoolsBlur!=null) blurTransaction.remove(powertoolsBlur).commit();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_powertools_menu, container, false);
    }
}