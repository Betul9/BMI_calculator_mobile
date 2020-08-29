package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private String[] height_units={"m", "cm", "ft", "in"};
    private String[] weight_units={"oz", "lb", "gr", "kg"};

    private Spinner spinnerHeight;
    private Spinner spinnerWeight;
    private ArrayAdapter<String> dataAdapterForHeight;
    private ArrayAdapter<String> dataAdapterForWeight;

    EditText editTWeight, editTHeight;
    TextView result_numeric, result;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerHeight = (Spinner) findViewById(R.id.spinner_H);
        spinnerWeight = (Spinner) findViewById(R.id.spinner_W);

        dataAdapterForHeight = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, height_units);
        dataAdapterForWeight = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, weight_units);

        dataAdapterForHeight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterForWeight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerHeight.setAdapter(dataAdapterForHeight);
        spinnerWeight.setAdapter(dataAdapterForWeight);

        editTWeight = (EditText) findViewById(R.id.editTWeight);
        editTHeight = (EditText)findViewById(R.id.editTHeight);
        result_numeric = (TextView) findViewById(R.id.result_numeric);
        button = (Button) findViewById(R.id.button);
        result = (TextView) findViewById(R.id.result);
    }

    public void BMIcalculation(View view){

        String w;
        double wg= 0.00;
        if(editTWeight.getText() != null) {
            w = editTWeight.getText().toString();
            wg = Double.parseDouble(w);
            if (spinnerWeight.getSelectedItem() == "kg") {
                wg = wg;
            } else if (spinnerWeight.getSelectedItem() == "gr") {
                wg = wg / 1000;
            } else if (spinnerWeight.getSelectedItem() == "lb") {
                wg = wg * 0.4535;
            } else if (spinnerWeight.getSelectedItem() == "oz") {
                wg = wg * 0.02834;
            }
        }

        String h="";
        double hg = 0.00;

        if(editTHeight != null){
            h = editTHeight.getText().toString();
            hg = Double.parseDouble(h);
            if (spinnerHeight.getSelectedItem() == "m") {
                hg = hg;
            }
            else if (spinnerHeight.getSelectedItem() == "cm") {
                hg = hg / 100;
            }
            else if(spinnerHeight.getSelectedItem() == "ft"){
                hg = hg/3.28083;
            }
            else if(spinnerHeight.getSelectedItem() == "in"){
                hg = hg/39.37007;
            }
        }

        double resultnum = wg / (hg*hg);

        DecimalFormat f = new DecimalFormat("##.####");

        result_numeric.setText(f.format(resultnum));

        if(resultnum < 18.5)
            result.setText("Underweight");
        else if(resultnum >= 18.5 && resultnum < 25)
            result.setText("Normal");
        else if(resultnum >= 25 && resultnum < 30)
            result.setText("Owerweight");
        else if(resultnum >= 30 && resultnum < 35)
            result.setText("Obese");
        else if(resultnum >= 35)
            result.setText("Extremely Obese");


    }
}