package com.NaxaCo.Naxa;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.NaxaCo.Naxa.Fragments.MapModelFragment;

public class MapsActivity extends AppCompatActivity{
    MapModelFragment mapModelFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_layout);
        mapModelFragment = new MapModelFragment();
        FragmentManager manager=getFragmentManager();
        manager.beginTransaction().replace(R.id.map_holder, mapModelFragment).commit();
    }
}