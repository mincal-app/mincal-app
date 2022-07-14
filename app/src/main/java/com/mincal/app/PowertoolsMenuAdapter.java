package com.mincal.app;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.HashMap;

public class PowertoolsMenuAdapter extends BaseAdapter {
    private Context mContext;
    private int[] powerArray;

    // Constructor
    public PowertoolsMenuAdapter(Context c, int[] array) {
        mContext = c;
        powerArray = array;
    }

    public int getCount() {
        return powerArray.length;
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
            powertoolIcon.setLayoutParams(new GridView.LayoutParams(70, 70));
            powertoolIcon.setScaleType(ImageView.ScaleType.CENTER);
            powertoolIcon.setPadding(15, 15, 15, 15);
        } else {
            powertoolIcon = (ImageView) convertView;
        }

        powertoolIcon.setImageResource(powerArray[position]);
        return powertoolIcon;
    }

    // Keep all Images in array.

    public Integer[] mThumbIds = {
            R.drawable.ic_add_powertool,
            R.drawable.ic_chart_white,
            R.drawable.ic_chart_white,
            R.drawable.ic_chart_white
    };
}
