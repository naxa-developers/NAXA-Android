package com.NaxaCo.Naxa;

import android.Manifest;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.NaxaCo.Naxa.Conversion.UnitConversion;
import com.NaxaCo.Naxa.Conversion.UnitDto;
import com.NaxaCo.Naxa.Fragments.MapModelFragment;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.io.IOException;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

import static com.NaxaCo.Naxa.Fragments.MapModelFragment.mMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    MapModelFragment mapModelFragment;
    UnitConversion unitConversion;
    UnitDto unitDto;
    private FloatingActionMenu fam;
    private FloatingActionButton fabEdit, fabDelete, fabAdd;
    MaterialSearchView searchView;
    MaterialSpinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        unitConversion = new UnitConversion();
        unitDto=new UnitDto();
        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getApplicationContext(), "Hi", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        fabAdd = (FloatingActionButton) findViewById(R.id.fab2);
        fabDelete = (FloatingActionButton) findViewById(R.id.fab3);
        fabEdit = (FloatingActionButton) findViewById(R.id.fab1);
        fam = (FloatingActionMenu) findViewById(R.id.fab_menu);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Hello World", Toast.LENGTH_SHORT).show();
            }
        });
        mapModelFragment = new MapModelFragment();
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.map_holder, mapModelFragment).commit();

        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION};
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;

    }

    @Override
    public void onBackPressed() {
        //   DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //     searchView = (MaterialSearchView) findViewById(R.id.search_view);
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
        /* if (drawer.isDrawerOpen(GravityCompat.START)|| searchView.isSearchOpen()) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_conversion) {
            Toast.makeText(getApplicationContext(), "C", Toast.LENGTH_SHORT).show();
            inputDialogUnitConversion();
            /*
            Toast.makeText(getApplicationContext(),"h",Toast.LENGTH_SHORT).show();
         mapModelFragment.drawPolyline();*/
            //   mapModelFragment.test(mMap);
            //   Toast.makeText(getApplicationContext(),"Hi",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(getApplicationContext(), "gg", Toast.LENGTH_SHORT).show();

            //    mapModelFragment.test(mMap);
            //   FragmentManager fm = getFragmentManager();

//if you added fragment via layout xml
            //     MapModelFragment fragment = (MapModelFragment) fm.findFragmentById(R.id.map);
            //       fragment.drawPolyline();
            // Toast.makeText(getApplicationContext(),"HEllo World",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(getApplicationContext(), "Computer", Toast.LENGTH_SHORT).show();
            // test(mMap);
            /*
            LatLng sydeny=new LatLng(15.0,121.0);
           mMap.addMarker(new MarkerOptions().position(sydeny).title("HEllo World"));*/
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

            // fragment.yourPublicMethod();
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void inputDialogUnitConversion() {
        /*  private double bigha;
    private double katha;
    private double dhur;
    private double ropani;
    private double khetmuri;
    private double aana;
    private double paisa;
    private double daam;
    private double meterSquare;
    private double squareFeet;*/

        Dialog dialog = new Dialog(this);
        final EditText editText;
        final EditText bigha;
        final EditText katha;
        final EditText dhur;
        final EditText ropani;
        final EditText khetmuri;
        final EditText aana;
        final EditText paisa;
        final EditText daam;
        final EditText meterSquare;
        final EditText squareFeet;
        FancyButton convertButton;
        View view = LayoutInflater.from(this).inflate(R.layout.unit_conversion_input_dialog, null);
        spinner = view.findViewById(R.id.spinner);
        editText = view.findViewById(R.id.inputUnit);
        convertButton=view.findViewById(R.id.Convert);
        bigha=view.findViewById(R.id.biggha);
        katha=view.findViewById(R.id.katha);
        dhur=view.findViewById(R.id.dhur);
        ropani=view.findViewById(R.id.ropani);
        khetmuri=view.findViewById(R.id.khetmuri);
        aana=view.findViewById(R.id.aana);
        paisa=view.findViewById(R.id.paisa);
        daam=view.findViewById(R.id.daam);
        meterSquare=view.findViewById(R.id.meterSquare);
        squareFeet=view.findViewById(R.id.squareFeet);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(null);
            }
        });
        spinner.setItems("Select Unit", "Bigha", "Katha", "Dhur", "Ropani", "Khetmuri", "Aana", "Paisa", "Daam", "Meter square", "Square feet");
        //spinner.getItems();
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                }
        });
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    Toast.makeText(getApplicationContext(), "" + spinner.getSelectedIndex(), Toast.LENGTH_SHORT).show();
                unitConversion.convertUnit(Double.parseDouble(editText.getText().toString()), spinner.getSelectedIndex());
                Toast.makeText(getApplicationContext(),""+Double.parseDouble(editText.getText().toString()),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),""+String.valueOf(unitDto.getBigha()),Toast.LENGTH_SHORT).show();
                bigha.setText(String.valueOf(unitDto.getBigha()));
                katha.setText(String.valueOf(unitDto.getKatha()));
                dhur.setText(String.valueOf(unitDto.getDhur()));
                ropani.setText(String.valueOf(unitDto.getRopani()));
                khetmuri.setText(String.valueOf(unitDto.getKhetmuri()));
                aana.setText(String.valueOf(unitDto.getAana()));
                paisa.setText(String.valueOf(unitDto.getPaisa()));
                daam.setText(String.valueOf(unitDto.getDaam()));
                meterSquare.setText(String.valueOf(unitDto.getMeterSquare()));
                squareFeet.setText(String.valueOf(unitDto.getSquareFeet()));
            }
        });

        dialog.setContentView(view);
        dialog.show();
    }


}
