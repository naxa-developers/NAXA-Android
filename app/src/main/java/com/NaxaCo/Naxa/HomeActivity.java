package com.NaxaCo.Naxa;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.NaxaCo.Naxa.DbAccess.Conversion_Area;
import com.NaxaCo.Naxa.Plotter.FreeDraw;

import java.io.ByteArrayOutputStream;

public class HomeActivity extends AppCompatActivity {
    Button save;
    Button area;
    Conversion_Area cConversion_area;
    LinearLayout showConversion;
    RelativeLayout vDisplay_View;
    EditText vRapd;
    EditText vBkd;
    EditText vBiggha;
    EditText vKatha;
    EditText vDhur;
    EditText vKhetmuri;
    EditText vRopani;
    EditText vAaana;
    EditText vMatomuri;
    EditText vKM;
    EditText vAcre;
    EditText vSqKm;
    EditText vSqueareM;
    TextView vConversion_Title;
    Bitmap image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        vConversion_Title = findViewById(R.id.conversion_title);
        vDisplay_View = findViewById(R.id.display_View);
        vConversion_Title.setVisibility(View.GONE);
        showConversion = findViewById(R.id.show_conversion);
        showConversion.setVisibility(View.GONE);
        vRapd = findViewById(R.id.conversion_rapd);
        vBkd = findViewById(R.id.conversion_bkd);
        vBiggha = findViewById(R.id.conversion_biggha);
        vKatha = findViewById(R.id.conversion_kathha);
        vDhur = findViewById(R.id.conversion_dhur);
        vKhetmuri = findViewById(R.id.conversion_khetmuri);
        vRopani = findViewById(R.id.conversion_ropani);
        vAaana = findViewById(R.id.conversion_aana);
        vMatomuri = findViewById(R.id.conversion_matomuri);
        vKM = findViewById(R.id.conversion_km);
        vAcre = findViewById(R.id.conversion_acre);
        vSqKm = findViewById(R.id.conversion_squareKm);
        vSqueareM = findViewById(R.id.converions_squareM);
        area = findViewById(R.id.area);
        save = findViewById(R.id.save);
        area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConversion.setVisibility(View.VISIBLE);
                vConversion_Title.setVisibility(View.VISIBLE);
                String test = "100";
                vRapd.setText(test);
                vBkd.setText(test);
                vBiggha.setText(test);
                vKatha.setText(test);
                vDhur.setText(test);
                vKhetmuri.setText(test);
                vRopani.setText(test);
                vAaana.setText(test);
                vMatomuri.setText(test);
                vKM.setText(test);
                vAcre.setText(test);
                vSqKm.setText(test);
                vSqueareM.setText(test);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cConversion_area = new Conversion_Area(getApplicationContext());
                View view = (LayoutInflater.from(HomeActivity.this)).inflate(R.layout.area_conversion_save_input_dialog, null);

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(HomeActivity.this);
                alertBuilder.setView(view);
                alertBuilder.setTitle("Input Info");
                final EditText image_name = view.findViewById(R.id.name);

                alertBuilder.setCancelable(true)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    String imageName = image_name.getText().toString();
                                    vDisplay_View.setDrawingCacheEnabled(true);
                                    image = vDisplay_View.getDrawingCache();
                                    ContentValues contentValues=new ContentValues();
                                    contentValues.put("name",imageName);
                                    contentValues.put("image",getBlob(image));
                                    cConversion_area.insertInfo(contentValues);
                                    Toast.makeText(getApplicationContext(),"Saved Sucessfully",Toast.LENGTH_SHORT).show();
                                }
                                catch (Exception e){
                                    Toast.makeText(getApplicationContext(),"Input Image Name",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                Dialog dialog = alertBuilder.create();
                dialog.show();
            }
        });


    }
    public static byte[] getBlob(Bitmap bitmap){
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,bos);
        byte[] bArray=bos.toByteArray();
        return bArray;
    }
    public static Bitmap getBimap(byte[] byteArray){
        return BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
    }
}
