package com.musings.fufufanatic.coolconverterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    EditText enterValueET;
    Spinner convertFromQualifierSpinner, convertToQualifierSpinner;
    Button convertButton;
    TextView resultTextView;
    SpinnerAdapter convertToSpinnerAdapter;

    private void setupFromQualifierLister() {

        convertFromQualifierSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        enterValueET = (EditText)findViewById(R.id.enterValueET);
        convertFromQualifierSpinner = (Spinner)findViewById(R.id.convertFromQualifierSpinner);
        convertToQualifierSpinner = (Spinner)findViewById(R.id.convertToQualifierSpinner);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        convertButton = (Button)findViewById(R.id.convertButton);
        resultTextView = (TextView)findViewById(R.id.resultTextView);

        setupFromQualifierLister();
    }


    private void generateConvertToSpinner(String qualifier){



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
