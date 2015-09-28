package com.musings.fufufanatic.coolconverterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    EditText valueEditText;
    Spinner convertTypeQualifierSpinner, convertFromQualifierSpinner,convertToQualifierSpinner;
    Button convertButton;
    TextView resultTextView;
    SpinnerAdapter convertFromQualifierSpinnerAdapter, convertToQualifierSpinnerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        valueEditText = (EditText)findViewById(R.id.valueEditText);
        convertTypeQualifierSpinner = (Spinner)findViewById(R.id.convertTypeQualifierSpinner);
        convertFromQualifierSpinner = (Spinner)findViewById(R.id.convertFromQualifierSpinner);
        convertToQualifierSpinner = (Spinner)findViewById(R.id.convertToQualifierSpinner);
        resultTextView = (TextView)findViewById(R.id.resultValueTextView);
        convertButton = (Button)findViewById(R.id.convertButton);
        resultTextView = (TextView)findViewById(R.id.resultValueTextView);

        setupConvertTypeQualifierSpinner();
        setupConvertFromQualifierListener();
    }

    private void setupConvertTypeQualifierSpinner(){

        final ArrayAdapter<CharSequence> weightAdapter =  ArrayAdapter.createFromResource(this,R.array.weightTypesArray,android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> distanceAdapter = ArrayAdapter.createFromResource(this,R.array.distanceTypesArray,android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> temperatureAdapter = ArrayAdapter.createFromResource(this,R.array.temperatureTypesArray,android.R.layout.simple_spinner_item);

        convertTypeQualifierSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getSelectedItem().equals("Temperature")){

                    temperatureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    convertFromQualifierSpinner.setAdapter(temperatureAdapter);
                    convertToQualifierSpinner.setAdapter(null);
                    //resultTextView.setText("1");

                }else if (parent.getSelectedItem().equals("Weight")){

                    weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    convertFromQualifierSpinner.setAdapter(weightAdapter);
                    convertToQualifierSpinner.setAdapter(null);
                   // resultTextView.setText("2");

                } else if (parent.getSelectedItem().equals("Distance")) {

                    distanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    convertFromQualifierSpinner.setAdapter(distanceAdapter);
                    convertToQualifierSpinner.setAdapter(null);
                   // resultTextView.setText("3");
                } else {

                     // resultTextView.setText("0.0");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setupConvertFromQualifierListener() {

        final ArrayAdapter<CharSequence> weightAdapter =  ArrayAdapter.createFromResource(this,R.array.weightTypesArray,android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> distanceAdapter = ArrayAdapter.createFromResource(this,R.array.distanceTypesArray,android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> temperatureAdapter = ArrayAdapter.createFromResource(this,R.array.temperatureTypesArray,android.R.layout.simple_spinner_item);

        convertFromQualifierSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if (parent.getSelectedItem().equals("Celsius") ||
               (parent.getSelectedItem().equals("Fahrenheit")) ||
               (parent.getSelectedItem().equals("Kelvin")) ){

                convertToQualifierSpinner.setAdapter(temperatureAdapter);

            } else if (parent.getSelectedItem().equals("Pounds") ||
                      parent.getSelectedItem().equals("Ounces") ||
                      parent.getSelectedItem().equals("Kilograms")||
                      parent.getSelectedItem().equals("Milligrams")){

                convertToQualifierSpinner.setAdapter(weightAdapter);

            } else if (parent.getSelectedItem().equals("Inches")||
                       parent.getSelectedItem().equals("Feet")||
                       parent.getSelectedItem().equals("Yards")){

                convertToQualifierSpinner.setAdapter(distanceAdapter);
            } else {

            }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

