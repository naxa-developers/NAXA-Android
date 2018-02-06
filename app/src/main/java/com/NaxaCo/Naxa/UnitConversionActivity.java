package com.NaxaCo.Naxa;

import android.app.Dialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.NaxaCo.Naxa.Conversion.UnitConversion;
import com.jaredrummler.materialspinner.MaterialSpinner;

import mehdi.sakout.fancybuttons.FancyButton;

public class UnitConversionActivity extends AppCompatActivity {
    UnitConversion unitConversion;
    MaterialSpinner spinner;
    EditText editText;
    FancyButton convertButton;
    EditText bigha;
    EditText katha;
    EditText dhur;
    EditText ropani;
    EditText khetmuri;
    EditText aana;
    EditText paisa;
    EditText daam;
    EditText meterSquare;
    EditText squareFeet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        unitConversion=new UnitConversion(this);
        setContentView(R.layout.unit_conversion_input_dialog);
        spinner = findViewById(R.id.spinner);
        spinner.setItems("Select Unit", "Bigha", "Katha", "Dhur", "Ropani", "Khetmuri", "Aana", "Paisa", "Daam", "Meter square", "Square feet");

        editText = findViewById(R.id.inputUnit);
        convertButton=findViewById(R.id.Convert);
        bigha=findViewById(R.id.biggha);
        katha=findViewById(R.id.katha);
        dhur=findViewById(R.id.dhur);
        ropani=findViewById(R.id.ropani);
        khetmuri=findViewById(R.id.khetmuri);
        aana=findViewById(R.id.aana);
        paisa=findViewById(R.id.paisa);
        daam=findViewById(R.id.daam);
        meterSquare=findViewById(R.id.meterSquare);
        squareFeet=findViewById(R.id.squareFeet);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    unitConversion.convertUnit(Double.parseDouble(editText.getText().toString()), spinner.getSelectedIndex());
                    bigha.setText(String.valueOf(unitConversion.getBigha()));
                    katha.setText(String.valueOf(unitConversion.getKatha()));
                    dhur.setText(String.valueOf(unitConversion.getDhur()));
                    ropani.setText(String.valueOf(unitConversion.getRopani()));
                    khetmuri.setText(String.valueOf(unitConversion.getKhetmuri()));
                    aana.setText(String.valueOf(unitConversion.getAana()));
                    paisa.setText(String.valueOf(unitConversion.getPaisa()));
                    daam.setText(String.valueOf(unitConversion.getDaam()));
                    meterSquare.setText(String.valueOf(unitConversion.getMeterSquare()));
                    squareFeet.setText(String.valueOf(unitConversion.getSquareFeet()));
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Invalid Input",Toast.LENGTH_LONG).show();
                    //    Snackbar.make(view,"Invalid Input",Snackbar.LENGTH_SHORT).show();
                }

            }

        });
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(null);
            }
        });
    }
}
