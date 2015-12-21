package com.example.aitor.examenprom2;


import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Ejercicio3 extends FragmentActivity {

    private GoogleMap mMap;
    private final LatLng CiudadJardin= new LatLng(42.84057601472622, -2.674350649999951);
    private final LatLng HospitalSantiago = new LatLng(42.8466068, -2.66700620000006);
    private final LatLng RestauranteDonProducto = new LatLng(42.8431079, -2.6807655999999724);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio3);
        setUpMapIfNeeded();
        SupportMapFragment spmap = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        mMap = spmap.getMap();
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.getUiSettings().setCompassEnabled(true);


        mMap.addMarker(new MarkerOptions()
                .position(CiudadJardin)
                .title("Ciudad Jardin")
                .snippet("Centro de Formación Profesional")
                .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_compass))
                .anchor(0.5f, 0.5f));

        mMap.addMarker(new MarkerOptions()
                .position(HospitalSantiago)
                .title("Hospital de Santiago")
                .snippet("Hospital Centrico")
                .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_media_play))
                .anchor(0.5f, 0.5f));

        mMap.addMarker(new MarkerOptions()
                .position(RestauranteDonProducto)
                .title("Restaurante Don Producto")
                .snippet("Restaurante tradicional")
                .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_add))
                .anchor(0.5f, 0.5f));

    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
}
