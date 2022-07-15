package com.mincal.app;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class PowerIcons {

    private ArrayList<String> powertoolIds;

    public PowerIcons() {
        // Empty constructor
    }

    public PowerIcons(ArrayList<String> powertoolIds) {
        this.powertoolIds = powertoolIds;
    }

    // Methods

    public HashMap<String, Integer> getPowertools() {

        HashMap<String, Integer> powertoolsMap = new HashMap<>();

        // Powertools Dictionary

        for (int i = 0; i < powertoolIds.size(); i++) {

            String id = powertoolIds.get(i);
            Integer drawableId = 0;

            // Get drawable

            if (id.equals("geometry_set")) {
                drawableId = R.drawable.ic_chart_white;
            } else if (id.equals("statistics_set")) {
                drawableId = R.drawable.ic_chart_white;
            } else if (id.equals("algebra_set")) {
                drawableId = R.drawable.ic_chart_white;
            }

            // Add to ArrayList

            Log.d("PIcons", "Current id: " + drawableId);
            powertoolsMap.put(id, drawableId);
        }

        Log.d("PIcons", "Final id: " + powertoolsMap);

        return powertoolsMap;
    }
}
