package com.akivagrobman.bmicalculator.adapters;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.akivagrobman.bmicalculator.MainActivity;

public class SexAdapter implements AdapterView.OnItemSelectedListener {

    public static final String[] sex = {"Male", "Female"};
    private String selected;
    private final Context context;

    public SexAdapter(Context context) {
        this.context = context;
        selected = "";
    }

    public String getSelected() {
        return selected;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selected = sex[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
