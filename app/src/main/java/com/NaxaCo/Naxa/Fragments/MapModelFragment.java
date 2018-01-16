package com.NaxaCo.Naxa.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.NaxaCo.Naxa.MainActivity;
import com.NaxaCo.Naxa.MapsActivity;
import com.NaxaCo.Naxa.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shri on 13/01/2018.
 */

public class MapModelFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener {
    private static final String TAG = MapsActivity.class.getSimpleName();
    public static GoogleMap mMap;
    private CameraPosition oCameraPosition;
    private GeoDataClient oGeoDataClient;
    private PlaceDetectionClient oPlaceDetectionClient;
    private FusedLocationProviderClient oFusedLocationProviderClient;
    private final LatLng vDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int vDEFAULT_ZOOM = 15;
    private static final int cvPERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean cLocationPermissionGranted;
    private Location oLastKnownLocation = null;
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";
    private static final int M_MAX_ENTRIES = 5;
    private String[] mLikelyPlaceNames;
    private String[] mLikelyPlaceAddresses;
    private String[] mLikelyPlaceAttributions;
    private LatLng[] mLikelyPlaceLatLngs;
    private ArrayList<LatLng> arrayPoints = null;
    PolylineOptions polylineOptions;
    private boolean checkClick = false;
    List<LatLng> latLngs;
    double polylineLengthLat;
    double polylineLengthLng;
    double polylineLengthLat1;
    double polylineLengthLng1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_maps, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            oLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            oCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }
        arrayPoints = new ArrayList<LatLng>();
        MapFragment fragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        oGeoDataClient = Places.getGeoDataClient(getActivity(), null);
        oPlaceDetectionClient = Places.getPlaceDetectionClient(getActivity(), null);
        oFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        latLngs = new ArrayList<>();
        fragment.getMapAsync(this);
        getLocationPermission();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mMap != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, mMap.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, oLastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
              return;
        }
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnMarkerClickListener(this);
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(27.700769, 85.300140))
                .title("Kathmandu")
        );

        updateLocation();

        getDeviceLocation();

        showCurrentPlace();
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

    }

    private void getDeviceLocation() {

        try {
            if (cLocationPermissionGranted) {
                Task<Location> locationResult = oFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(getActivity(), new OnCompleteListener<Location>() {
                    double latitude;
                    double longititude;


                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {

                            oLastKnownLocation = task.getResult();
                            if (oLastKnownLocation != null) {
                                latitude = oLastKnownLocation.getLatitude();
                                longititude = oLastKnownLocation.getLongitude();
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longititude), vDEFAULT_ZOOM));
                                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                            }
                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            mMap.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(vDefaultLocation, vDEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            cLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    cvPERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        cLocationPermissionGranted = false;
        switch (requestCode) {
            case cvPERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    cLocationPermissionGranted = true;
                }
            }
        }
        updateLocation();
    }

    private void updateLocation() {
        if (mMap == null) {
            return;
        } else {
            try {
                if (cLocationPermissionGranted) {
                    mMap.setMyLocationEnabled(true);
                    mMap.getUiSettings().setMyLocationButtonEnabled(true);
                    mMap.getUiSettings().setCompassEnabled(true);
                } else {
                    mMap.setMyLocationEnabled(false);
                    mMap.getUiSettings().setMyLocationButtonEnabled(false);
                    oLastKnownLocation = null;
                    getLocationPermission();
                }
            } catch (SecurityException e) {
                Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void openPlacesDialog() {
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LatLng markerLatLng = mLikelyPlaceLatLngs[which];
                String markerSnippet = mLikelyPlaceAddresses[which];
                if (mLikelyPlaceAttributions[which] != null) {
                    markerSnippet = markerSnippet + "\n" + mLikelyPlaceAttributions[which];
                }
                mMap.addMarker(new MarkerOptions()
                        .title(mLikelyPlaceNames[which])
                        .position(markerLatLng)
                        .snippet(markerSnippet));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerLatLng,
                        vDEFAULT_ZOOM));
            }
        };
/*
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.default_snippet_info)
                .setItems(mLikelyPlaceNames, listener)
                .show();*/
    }

    private void showCurrentPlace() {
        if (mMap == null) {
            return;
        }
        if (cLocationPermissionGranted) {
            @SuppressLint("MissingPermission") final Task<PlaceLikelihoodBufferResponse> placeResult =
                    oPlaceDetectionClient.getCurrentPlace(null);
            placeResult.addOnCompleteListener
                    (new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
                        @Override
                        public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task) {
                            if (task.isSuccessful() && task.getResult() != null) {
                                PlaceLikelihoodBufferResponse likelyPlaces = task.getResult();
                                int count;
                                if (likelyPlaces.getCount() < M_MAX_ENTRIES) {
                                    count = likelyPlaces.getCount();
                                } else {
                                    count = M_MAX_ENTRIES;
                                }

                                int i = 0;
                                mLikelyPlaceNames = new String[count];
                                mLikelyPlaceAddresses = new String[count];
                                mLikelyPlaceAttributions = new String[count];
                                mLikelyPlaceLatLngs = new LatLng[count];

                                for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                                    mLikelyPlaceNames[i] = (String) placeLikelihood.getPlace().getName();
                                    mLikelyPlaceAddresses[i] = (String) placeLikelihood.getPlace()
                                            .getAddress();
                                    mLikelyPlaceAttributions[i] = (String) placeLikelihood.getPlace()
                                            .getAttributions();
                                    mLikelyPlaceLatLngs[i] = placeLikelihood.getPlace().getLatLng();

                                    i++;
                                    if (i > (count - 1)) {
                                        break;
                                    }
                                }

                                likelyPlaces.release();

                                openPlacesDialog();

                            } else {
                                Toast.makeText(getActivity(), "Error1", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        } else {

            Toast.makeText(getActivity(), "User did not grant location permission", Toast.LENGTH_SHORT).show();


            mMap.addMarker(new MarkerOptions()
                    .title(getString(R.string.title_activity_maps))
                    .position(vDefaultLocation)
                    .snippet(getString(R.string.default_snippet_info)));
            getLocationPermission();
        }
    }


    public void countPolygonPoints() {
       // if (arrayPoints.size() >= 3) {
            checkClick = true;
            PolygonOptions polygonOptions = new PolygonOptions();
            polygonOptions.addAll(arrayPoints);
            polygonOptions.strokeColor(Color.BLUE);
            polygonOptions.strokeWidth(7);
            polygonOptions.fillColor(Color.TRANSPARENT);
            Polygon polygon = mMap.addPolygon(polygonOptions);
     //   }
    }
    public void drawPolyline(){
        mMap.addPolyline(
                new PolylineOptions().
                        add(new LatLng(51.5,-0.1),new LatLng(40.7,-74.0))
                .width(5)
                .color(Color.GREEN));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
      //  if (arrayPoints.get(0).equals(marker.getPosition())) {
            countPolygonPoints();
       /* }
        else{
            Toast.makeText(getActivity(),"Hello World",Toast.LENGTH_SHORT).show();
            drawPolyline();
        }*/
        return false;
    }
    @Override
    public void onMapClick(LatLng point) {
        int check = 1;
        if (checkClick == false) {
            mMap.addMarker(new MarkerOptions().position(point));
            arrayPoints.add(point);
            polylineLengthLat=point.latitude;
            polylineLengthLng=point.longitude;
            polylineLengthLat1=point.latitude;
            polylineLengthLng1=point.longitude;
        }
        while (check == 1) {
            latLngs.add(new LatLng(point.latitude, point.longitude));
            check = 0;
        }
    }

    public void test(GoogleMap googleMap){
        LatLng sydeny=new LatLng(13.00,14.00);
       // mMap.addMarker(new MarkerOptions().position(sydeny).title("Hello world"));
        Toast.makeText(getActivity(),"Hello World",Toast.LENGTH_SHORT).show();
        googleMap.addMarker(new MarkerOptions().position(sydeny).title("Hello world"));
    }

    @Override
    public void onMapLongClick(LatLng point) {
        mMap.clear();
        arrayPoints.clear();
        checkClick = false;
        Toast.makeText(getActivity(), "Area:" + SphericalUtil.computeArea(latLngs), Toast.LENGTH_SHORT).show();
    }


}

