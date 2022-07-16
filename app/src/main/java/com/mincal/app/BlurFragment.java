package com.mincal.app;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class BlurFragment extends Fragment {

  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  private String mParam1;
  private String mParam2;

  public BlurFragment() {
    // Required empty public constructor
  }

  public static BlurFragment newInstance(String param1, String param2) {
    BlurFragment fragment = new BlurFragment();
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

    // Views

    BlurView blurView = getView().findViewById(R.id.powertools_menu_blur);

    // Blur properties.

    float radius = 0.3f;

    View decorView = getActivity().getWindow().getDecorView();
    ViewGroup rootView =
        (ViewGroup)decorView.findViewById(android.R.id.content);
    Drawable windowBackground = decorView.getBackground();

    blurView.setupWith(rootView)
        .setFrameClearDrawable(windowBackground) // Optional
        .setBlurAlgorithm(new RenderScriptBlur(getContext()))
        .setBlurRadius(radius)
        .setBlurAutoUpdate(true);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_blur, container, false);
  }
}