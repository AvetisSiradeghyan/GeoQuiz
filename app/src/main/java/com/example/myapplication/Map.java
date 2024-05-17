package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Map extends AppCompatActivity implements OnMapReadyCallback {


    Button profile;

    float zoomLevel = 7;
    private GoogleMap map;
    ArrayList<LatLng> arrayList = new ArrayList<>();

    LatLng zoom = new LatLng(40, 44.8);
    LatLng Vanadzor = new LatLng(40.81450196869515, 44.48257323317036);
    LatLng Yerevan = new LatLng(40.177200, 44.503490);
    LatLng Gyumri = new LatLng(40.7929026, 43.8464971);
    LatLng Dilijan = new LatLng(40.7545444,44.8989488 );
    ArrayList<String> title = new ArrayList<String>();
    FirebaseUser mUser;
    ArrayList<Map_Model> map_model = new ArrayList<>();







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

//        profile = findViewById(R.id.profile);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            mUser = extras.getParcelable("auth");
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            FirebaseFirestore.getInstance().collection("Quiz")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots){
                                map_model.add(new Map_Model(queryDocumentSnapshot.getString("Town"), queryDocumentSnapshot.getDouble("Lat"), queryDocumentSnapshot.getDouble("Lng")));
                            }
                            mapFragment.getMapAsync(Map.this);


                        }
                    });
        }






    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        for (int i = 0; i<map_model.size(); i++){
                LatLng latLng = new LatLng(map_model.get(i).Lat, map_model.get(i).Lng);
                map.addMarker(new MarkerOptions().position(latLng).title(map_model.get(i).Town));
                map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
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