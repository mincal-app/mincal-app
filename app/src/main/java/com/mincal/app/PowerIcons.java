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

    public HashMap<String, ArrayList<Integer>> getPowertools() {

        HashMap<String, ArrayList<Integer>> powertoolsMap = new HashMap<>();

        // Powertools Dictionary

        for (int i = 0; i < powertoolIds.size(); i++) {

            String id = powertoolIds.get(i);
            String idName = "";
            ArrayList<Integer> drawableIds = new ArrayList<>();

            // Get drawable

            if ("geometry_set".equals(id)) {
                idName = "Statistics";
                drawableIds.add(R.drawable.ic_sin_geometry);
                drawableIds.add(R.drawable.ic_cos_geometry);
                drawableIds.add(R.drawable.ic_tan_geometry);
                drawableIds.add(R.drawable.ic_sec_geometry);
                drawableIds.add(R.drawable.ic_csc_geometry);
                drawableIds.add(R.drawable.ic_cot_geometry);
                drawableIds.add(R.drawable.ic_arcsin_geometry);
                drawableIds.add(R.drawable.ic_arccos_geometry);
                drawableIds.add(R.drawable.ic_arctan_geometry);
                drawableIds.add(R.drawable.ic_pi_geometry);
            } else if ("statistics_set".equals(id)) {
                idName = "Statistics";
                drawableIds.add(R.drawable.ic_npr);
                drawableIds.add(R.drawable.ic_ncr);
            } else if ("algebra_set".equals(id)) {
                idName = "Algebra";
                drawableIds.add(R.drawable.ic_log);
                drawableIds.add(R.drawable.ic_percent);
            }

            // Add to ArrayList

            powertoolsMap.put(idName, drawableIds);
        }

        return powertoolsMap;
    }
}
