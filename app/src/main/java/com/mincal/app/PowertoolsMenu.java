package com.mincal.app;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.ArrayList;
import java.util.HashMap;

public class PowertoolsMenu extends Fragment {

  // TinyDB

  TinyDB tinydb;
  String userColor;
  ArrayList<String> userPowertools;

  // Views

  TextView categoryButton;
  GridView powertoolsGrid;
  TextView hidePowertools;

  // Logic

  int powertoolsCounter = 0;
  String currentKey = "";

  // Animations

  Animation scaleUp, scaleDown;

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
  public void onViewCreated(@NonNull View view,
                            @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    // Instance of SharedPreferences to store the user's name.

    tinydb = new TinyDB(getContext());
    userColor = tinydb.getString("user_color");
    userPowertools = tinydb.getListString("user_powertools");

    // Views

    categoryButton = getView().findViewById(R.id.category_button);
    powertoolsGrid = (GridView)getView().findViewById(R.id.powertools_grid);
    hidePowertools = getView().findViewById(R.id.hide_powertools);

    // Getting icons, functions, and ids.

    PowerIcons powerIcons = new PowerIcons(userPowertools);
    HashMap<String, ArrayList<Integer>> powertoolsMap =
        powerIcons.getPowertools();

    /* Debug Logs */

    Log.d("PowerIcons", "HashMap contains: " + powertoolsMap);

    // Choose icons and PowerTools based on user preferences

    currentKey = (String)powertoolsMap.keySet().toArray()[powertoolsCounter];

    categoryButton.setText(
        (String)powertoolsMap.keySet().toArray()[powertoolsCounter]);
    powertoolsGrid.setAdapter(
        new PowertoolsMenuAdapter(getContext(), powertoolsMap.get(currentKey)));

    // Animations

    // Click animations

    scaleUp =
        AnimationUtils.loadAnimation(getContext(), R.anim.button_click_up);
    scaleDown =
        AnimationUtils.loadAnimation(getContext(), R.anim.button_click_down);

    // End of Animation Behavior

    scaleDown.setAnimationListener(new Animation.AnimationListener() {
      @Override
      public void onAnimationStart(Animation animation) {

        // Nothing
      }

      @Override
      public void onAnimationEnd(Animation animation) {

        Log.d("Powertools Menu", "Size: " + powertoolsMap.size());

        if (powertoolsCounter < powertoolsMap.size() - 1) {
          powertoolsCounter++;
        } else {
          powertoolsCounter = 0;
        }

        // Update category title.

        currentKey =
            (String)powertoolsMap.keySet().toArray()[powertoolsCounter];
        categoryButton.setText(currentKey);

        // Update icons.

        powertoolsGrid.setAdapter(new PowertoolsMenuAdapter(
            getContext(), powertoolsMap.get(currentKey)));
      }

      @Override
      public void onAnimationRepeat(Animation animation) {
        // Nothing
      }
    });

    // Hide PowerTools Menu on Close button tap.

    // Listeners

    hidePowertools.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        // Change powertoolsMenuOpened state.

        tinydb.putBoolean("powertools_menu_opened", false);

        // Hide powertools menu.

        FragmentManager fragmentManager =
            getActivity().getSupportFragmentManager();
        FragmentTransaction powerMenuTransaction =
            fragmentManager.beginTransaction();
        FragmentTransaction blurTransaction =
            fragmentManager.beginTransaction();

        powerMenuTransaction.setCustomAnimations(
            R.anim.swipe_out_left, R.anim.dummy_anim_swipe_left,
            R.anim.dummy_anim_swipe_left, R.anim.swipe_out_left);
        blurTransaction.setCustomAnimations(
            R.anim.fade_out, R.anim.dummy_anim_swipe_left,
            R.anim.dummy_anim_swipe_left, R.anim.fade_out);

        Fragment powertoolsMenu =
            getActivity().getSupportFragmentManager().findFragmentByTag(
                "blur_powertools_menu");
        Fragment powertoolsBlur =
            getActivity().getSupportFragmentManager().findFragmentByTag(
                "powertools_menu");
        if (powertoolsMenu != null)
          powerMenuTransaction.remove(powertoolsMenu).commit();
        if (powertoolsBlur != null)
          blurTransaction.remove(powertoolsBlur).commit();
      }
    });

    categoryButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        categoryButton.startAnimation(scaleUp);
        categoryButton.startAnimation(scaleDown);
      }
    });

    // Modify color based on user's selected color.

    if ("purple".equals(userColor)) {
      hidePowertools.setBackgroundResource(
          R.drawable.calculator_pad_rounded_left_purple);
      categoryButton.setBackgroundResource(R.drawable.rounded_category_purple);
      powertoolsGrid.setBackgroundResource(R.drawable.powertools_menu_purple);
    } else if ("orange".equals(userColor)) {
      hidePowertools.setBackgroundResource(
          R.drawable.calculator_pad_rounded_left_orange);
      categoryButton.setBackgroundResource(R.drawable.rounded_category_orange);
      powertoolsGrid.setBackgroundResource(R.drawable.powertools_menu_orange);
    } else if ("red".equals(userColor)) {
      hidePowertools.setBackgroundResource(
          R.drawable.calculator_pad_rounded_left_red);
      categoryButton.setBackgroundResource(R.drawable.rounded_category_red);
      powertoolsGrid.setBackgroundResource(R.drawable.powertools_menu_red);
    } else if ("green".equals(userColor)) {
      hidePowertools.setBackgroundResource(
          R.drawable.calculator_pad_rounded_left_green);
      categoryButton.setBackgroundResource(R.drawable.rounded_category_green);
      powertoolsGrid.setBackgroundResource(R.drawable.powertools_menu_green);
    } else if ("blue".equals(userColor)) {
      hidePowertools.setBackgroundResource(
          R.drawable.calculator_pad_rounded_left_blue);
      categoryButton.setBackgroundResource(R.drawable.rounded_category_blue);
      powertoolsGrid.setBackgroundResource(R.drawable.powertools_menu_blue);
    } else if ("yellow".equals(userColor)) {
      hidePowertools.setBackgroundResource(
          R.drawable.calculator_pad_rounded_left_yellow);
      categoryButton.setBackgroundResource(R.drawable.rounded_category_yellow);
      powertoolsGrid.setBackgroundResource(R.drawable.powertools_menu_yellow);
    } else {
      hidePowertools.setBackgroundResource(
          R.drawable.calculator_pad_rounded_left_purple);
      categoryButton.setBackgroundResource(R.drawable.rounded_category_purple);
      powertoolsGrid.setBackgroundResource(R.drawable.powertools_menu_purple);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_powertools_menu, container,
                            false);
  }
}
