package com.musings.fufufanatic.coolconverterapp;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

    private double inputValue;

    EditText inputValueEditText;
    Spinner convertTypeQualifierSpinner, convertFromQualifierSpinner,convertToQualifierSpinner;
    Button convertButton;
    TextView resultValueTextView;
    SpinnerAdapter convertFromQualifierSpinnerAdapter, convertToQualifierSpinnerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        inputValueEditText = (EditText)findViewById(R.id.inputValueEditText);
        convertTypeQualifierSpinner = (Spinner)findViewById(R.id.convertTypeQualifierSpinner);
        convertFromQualifierSpinner = (Spinner)findViewById(R.id.convertFromQualifierSpinner);
        convertToQualifierSpinner = (Spinner)findViewById(R.id.convertToQualifierSpinner);
        resultValueTextView = (TextView)findViewById(R.id.resultValueTextView);
        convertButton = (Button)findViewById(R.id.convertButton);

        setupConvertTypeQualifierSpinner();
        setupConvertFromQualifierListener();
        setupConvertButtonListener();

        inputValueEditText.addTextChangedListener(inputValueEditTextListener);

    }

    private void setupConvertTypeQualifierSpinner(){

        final ArrayAdapter<CharSequence> weightAdapter =  ArrayAdapter.createFromResource(this,R.array.weightTypesArray,android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> distanceAdapter = ArrayAdapter.createFromResource(this,R.array.distanceTypesArray,android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> temperatureAdapter = ArrayAdapter.createFromResource(this,R.array.temperatureTypesArray,android.R.layout.simple_spinner_item);

        convertTypeQualifierSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getSelectedItem().equals("Temperature")) {

                    temperatureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    convertFromQualifierSpinner.setAdapter(temperatureAdapter);
                    convertToQualifierSpinner.setAdapter(null);
                    resultValueTextView.setText("0.0");

                } else if (parent.getSelectedItem().equals("Weight")) {

                    weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    convertFromQualifierSpinner.setAdapter(weightAdapter);
                    convertToQualifierSpinner.setAdapter(null);
                    resultValueTextView.setText("0.0");

                } else if (parent.getSelectedItem().equals("Distance")) {

                    distanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    convertFromQualifierSpinner.setAdapter(distanceAdapter);
                    convertToQualifierSpinner.setAdapter(null);
                    resultValueTextView.setText("0.0");
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

    private void setupConvertButtonListener(){


        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateResultValue();
            }
        });
    }

    private TextWatcher inputValueEditTextListener = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            updateResultValue();
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                // Change the inputValue to the new input
                inputValue = Double.parseDouble(s.toString());
            } catch (NumberFormatException e) {
                inputValue = 0.0;
            }
        }
    };

    private void updateResultValue(){

        double result = 0.0;

        if (convertTypeQualifierSpinner.getSelectedItem().equals("Distance")) {

            if (String.valueOf(convertFromQualifierSpinner.getSelectedItem()).equals("Inches")) {
                if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Feet")) {
                    result = DistanceConversions.inchesToFeet(inputValue);
                } else if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Yards")) {
                    result = DistanceConversions.inchesToYards(inputValue);
                } else if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Centimeters")) {
                    result = DistanceConversions.inchesToCentimeters(inputValue);
                }
            } else if (String.valueOf(convertFromQualifierSpinner.getSelectedItem()).equals("Feet")) {
                if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Inches")) {
                    result = DistanceConversions.feetToInches(inputValue);
                } else if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Yards")) {
                    result = DistanceConversions.feetToYards(inputValue);
                } else if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Centimeters")) {
                    result = DistanceConversions.feetToCentimeters(inputValue);
                }
            } else if (String.valueOf(convertFromQualifierSpinner.getSelectedItem()).equals("Yards")) {
                if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Inches")) {
                    result = DistanceConversions.yardsToInches(inputValue);
                } else if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Feet")) {
                    result = DistanceConversions.yardsToFeet(inputValue);
                } else if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Centimeters")) {
                    result = DistanceConversions.yardsToCentimeters(inputValue);
                }
            } else if (String.valueOf(convertFromQualifierSpinner.getSelectedItem()).equals("Centimeters")) {
                if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Inches")) {
                    result = DistanceConversions.centimetersToInches(inputValue);
                } else if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Feet")) {
                    result = DistanceConversions.centimetersToFeet(inputValue);
                } else if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Yards")) {
                    result = DistanceConversions.centimetersToYards(inputValue);
                }
            }


        } else if (convertTypeQualifierSpinner.getSelectedItem().equals("Weight")){

            if (String.valueOf(convertFromQualifierSpinner.getSelectedItem()).equals("Pounds")) {
                if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Ounces")) {
                    result = WeightConversions.poundsToOunces(inputValue);
                } else if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Kilograms")) {
                    result = WeightConversions.poundsToKilograms(inputValue);
                } else if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Milligrams")) {
                    result = WeightConversions.poundsToMilligrams(inputValue);
                }
            } else if (String.valueOf(convertFromQualifierSpinner.getSelectedItem()).equals("Ounces")) {
                if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Kilograms")) {
                    result = WeightConversions.ouncesToKilograms(inputValue);
                } else if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Milligrams")) {
                    result = WeightConversions.ouncesToMilligrams(inputValue);
                } else if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Pounds")) {
                    result = WeightConversions.ouncesToPounds(inputValue);
                }
            } else if (String.valueOf(convertFromQualifierSpinner.getSelectedItem()).equals("Kilograms")) {
                if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Milligrams")) {
                    result = WeightConversions.kilogramsToMilligrams(inputValue);
                } else if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Pounds")) {
                    result = WeightConversions.kilogramsToPounds(inputValue);
                } else if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Ounces")) {
                    result = WeightConversions.kilogramsToOunces(inputValue);
                }
            } else if (String.valueOf(convertFromQualifierSpinner.getSelectedItem()).equals("Milligrams")) {
                if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Pounds")) {
                    result = WeightConversions.milligramsToPounds(inputValue);
                } else if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Ounces")) {
                    result = WeightConversions.milligramsToOunces(inputValue);
                } else if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Kilograms")) {
                    result =WeightConversions.milligramsToKilogram(inputValue);
                }
            }

        } else if (convertTypeQualifierSpinner.getSelectedItem().equals("Temperature")) {

            if (String.valueOf(convertFromQualifierSpinner.getSelectedItem()).equals("Celsius")) {
                if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Fahrenheit")) {
                    result = TemperatureConversions.celsiusToFahrenheit(inputValue);
                } else if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Kelvin")) {
                    result = TemperatureConversions.celsiusToKelvin(inputValue);
                }
            } else if (String.valueOf(convertFromQualifierSpinner.getSelectedItem()).equals("Fahrenheit")) {
                if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Kelvin")) {
                    result = TemperatureConversions.fahrenheitToKelvin(inputValue);
                } else if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Celsius")) {
                    result = TemperatureConversions.fahrenheitToCelsius(inputValue);
                }
            } else if (String.valueOf(convertFromQualifierSpinner.getSelectedItem()).equals("Kelvin")) {
                if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Celsius")) {
                    result = TemperatureConversions.kelvinToCelsius(inputValue);
                } else if (String.valueOf(convertToQualifierSpinner.getSelectedItem()).equals("Fahrenheit")) {
                    result = TemperatureConversions.kelvinToFahrenheit(inputValue);
                }
            }
        }

        resultValueTextView.setText(String.format("%.02f", result));
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