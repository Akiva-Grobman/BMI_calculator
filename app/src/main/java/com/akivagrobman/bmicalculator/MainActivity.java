package com.akivagrobman.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.akivagrobman.bmicalculator.adapters.BodyTypeAdapter;
import com.akivagrobman.bmicalculator.adapters.SexAdapter;
import static com.akivagrobman.bmicalculator.ResultsActivity.*;
import static com.akivagrobman.bmicalculator.adapters.BodyTypeAdapter.bodyTypes;
import static com.akivagrobman.bmicalculator.adapters.SexAdapter.sex;

public class MainActivity extends AppCompatActivity {

    private BodyTypeAdapter bodyTypeAdapter;
    private SexAdapter sexAdapter;
    private EditText firstName;
    private EditText lastName;
    private EditText age;
    private EditText height;
    private EditText weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initXMLComponents();
    }

    private void initXMLComponents() {
        bodyTypeAdapter = new BodyTypeAdapter(this);
        sexAdapter = new SexAdapter(this);
        setSpinner(R.id.body_type, bodyTypes, bodyTypeAdapter);
        setSpinner(R.id.sex, sex, sexAdapter);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        age = findViewById(R.id.age);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(this::submit);
    }

    private void setSpinner(int id, String[] options, AdapterView.OnItemSelectedListener adapter) {
        Spinner bodyType = findViewById(id);
        bodyType.setOnItemSelectedListener(adapter);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, options);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bodyType.setAdapter(aa);
    }

    public void submit(View view) {
        String firstName = this.firstName.getText().toString();
        String lastName = this.lastName.getText().toString();
        String age = this.age.getText().toString();
        String height = this.height.getText().toString();
        String weight = this.weight.getText().toString();
        String bodyType = bodyTypeAdapter.getSelected();
        String sex = sexAdapter.getSelected();
        if(isMissingField(firstName,
                lastName,
                age,
                height,
                weight,
                bodyType,
                sex)) {
            Toast.makeText(this, "Please set all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        double bmi = BMICalculator.calcBMI(toDouble(height), toDouble(weight));
        Intent resultsIntent = new Intent(this, ResultsActivity.class);
        resultsIntent.putExtra(USER_NAME_AND_SEX, String.format("%s %s (%s)", firstName, lastName, sex));
        resultsIntent.putExtra(BMI, bmi);
        resultsIntent.putExtra(WEIGHT, toDouble(weight));
        resultsIntent.putExtra(WEIGHT_STATUS, BMICalculator.getWeightStatus(bmi));
        resultsIntent.putExtra(IDEAL_WEIGHT, BMICalculator.getIdealWeight(toDouble(height), Integer.parseInt(age), bodyType));
        startActivity(resultsIntent);
    }

    private double toDouble(String stringVal) {
        return Double.parseDouble(stringVal);
    }

    private boolean isMissingField(String firstName, String lastName, String age, String height, String weight, String bodyType, String sex) {
        return firstName.isEmpty() || lastName.isEmpty() || age.isEmpty() || height.isEmpty() || weight.isEmpty() || bodyType.isEmpty() || sex.isEmpty();
    }

}