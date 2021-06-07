package com.akivagrobman.bmicalculator.adapters;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

public class BodyTypeAdapter implements AdapterView.OnItemSelectedListener {

    public static final String[] bodyTypes = {"Small", "Medium", "Large"};
    private final Context context;
    private String selected;

    public BodyTypeAdapter(Context context) {
        this.context = context;
        selected = "";
    }

    public String getSelected() {
        return selected;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selected = bodyTypes[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
