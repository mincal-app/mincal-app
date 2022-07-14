package com.mincal.app;

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
            int drawableId = 0;

            // Get drawable

            if (id == "geometry_set") {
                drawableId = R.id.algebra_set_text;
            } else if (id == "statistics_set") {
                drawableId = R.id.algebra_set_text;
            } else if (id == "algebra_set") {
                drawableId = R.id.algebra_set_text;
            }

            // Add to ArrayList

            powertoolsMap.put(powertoolIds.get(i), drawableId);
        }

        return powertoolsMap;
    }
}
