package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Map extends AppCompatActivity implements OnMapReadyCallback {



    float zoomLevel = 7;
    private GoogleMap map;
    ArrayList<LatLng> arrayList = new ArrayList<>();

    LatLng zoom = new LatLng(40, 44.8);
    LatLng Vanadzor = new LatLng(40.81450196869515, 44.48257323317036);
    LatLng Yerevan = new LatLng(40.177200, 44.503490);
    LatLng Gyumri = new LatLng(40.7929026, 43.8464971);
    LatLng Dilijan = new LatLng(40.7545444,44.8989488 );
    ArrayList<String> title = new ArrayList<String>();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        arrayList.add(Vanadzor);
        arrayList.add(Yerevan);
        arrayList.add(Gyumri);
        arrayList.add(Dilijan);

        title.add("Vanadzor");
        title.add("Yerevan");
        title.add("Gyumri");
        title.add("Dilijan");


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        for (int i = 0; i<arrayList.size(); i++){
            for (int j = 0; j<title.size(); j++){
                map.addMarker(new MarkerOptions().position(arrayList.get(i)).title(String.valueOf(title.get(j))));
            }
            map.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
        }



        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                String markertitle = marker.getTitle();

                Intent i = new Intent(Map.this, First.class);
                i.putExtra("title", markertitle);
                startActivity(i);

                return false;
            }
        });

        CameraPosition cameraPosition = new CameraPosition.Builder().target(zoom).zoom(zoomLevel).build();

        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);

        map.moveCamera(cameraUpdate);
    }
}