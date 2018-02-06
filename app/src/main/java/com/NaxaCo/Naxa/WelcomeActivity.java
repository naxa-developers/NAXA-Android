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

import java.util.zip.Inflater;

public class WelcomeActivity extends AppCompatActivity {
    Button plotter;
    Button conversion;
    Button geoData;
    ImageView MapImageView;
    ImageButton vInfoDisplay;
    ImageButton profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        plotter=findViewById(R.id.Plotter);
        conversion=findViewById(R.id.Conversion);
        vInfoDisplay=findViewById(R.id.info_display);
        vInfoDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this,Info.class);
                startActivity(intent);
            }
        });
        profile=findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this,MyProfile.class);
                startActivity(intent);
            }
        });
        geoData=findViewById(R.id.GeoData);
        geoData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this,MapsActivity.class);
            }
        });
        conversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this,UnitConversionActivity.class);
                startActivity(intent);
            }
        });
        MapImageView=findViewById(R.id.map);
        plotter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.welcome_activity_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.share:
                break;
            case R.id.likeUs:
                break;
            case R.id.About:
                break;
            case R.id.settings:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
