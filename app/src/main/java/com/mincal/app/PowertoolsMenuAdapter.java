package com.mincal.app;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

public class PowertoolsMenuAdapter extends BaseAdapter {
    private final Context mContext;
    private final ArrayList<Integer> powerArray;

    // Constructor
    public PowertoolsMenuAdapter(Context c, ArrayList<Integer> array) {
        mContext = c;
        powerArray = array;
    }

    public int getCount() {
        return powerArray.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // Create a new ImageView for each item referenced by the Adapter

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView powertoolIcon;
        Log.d("Adapter", "Drawable Array: " + powerArray);

        if (convertView == null) {
            powertoolIcon = new ImageView(mContext);
            powertoolIcon.setLayoutParams(new GridView.LayoutParams(110, 70));
            powertoolIcon.setScaleType(ImageView.ScaleType.CENTER);
            powertoolIcon.setPadding(15, 15, 15, 15);
        } else {
            powertoolIcon = (ImageView) convertView;
        }

        powertoolIcon.setImageResource(powerArray.get(position));
        return powertoolIcon;
    }
}
