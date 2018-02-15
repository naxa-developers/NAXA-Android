package com.NaxaCo.Naxa;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.NaxaCo.Naxa.MapCheck.MapCheck;

import java.util.zip.Inflater;

public class WelcomeActivity extends AppCompatActivity {
    Button plotter;
    Button conversion;
    Button geoData;
    ImageView MapImageView;
    ImageButton vInfoDisplay;
    ImageButton profile;
    MapCheck mapCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        plotter = findViewById(R.id.Plotter);
        conversion = findViewById(R.id.Conversion);
        vInfoDisplay = findViewById(R.id.info_display);
        vInfoDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, Info.class);
                startActivity(intent);
            }
        });
        profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    Intent intent = new Intent(WelcomeActivity.this, MyProfile.class);
              //  startActivity(intent);
            }
        });
        geoData = findViewById(R.id.GeoData);
        geoData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, MapsActivity.class);
            }
        });
        conversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, UnitConversionActivity.class);
                startActivity(intent);
            }
        });
        MapImageView = findViewById(R.id.map);
        MapImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
                builder.setTitle("Load Google Map");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent=new Intent(WelcomeActivity.this,StaticMapActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent=new Intent(WelcomeActivity.this,MapsActivity.class);
                        startActivity(intent);
                    }
                });
                   AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
        plotter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        }
        return super.onOptionsItemSelected(item);
    }
}
