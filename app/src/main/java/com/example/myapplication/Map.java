package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Map extends AppCompatActivity implements OnMapReadyCallback {

    LinearLayout bar;
    int score = Quiz.score;



//    public static boolean compl = Quiz.compl;

    Button profile;
    int visibility = 0;
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



    String Task_text = "Task";


    View view1;

    TextView total_score_view, average;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);



        profile = findViewById(R.id.profile);




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

    public void signOut(View view) {
        AlertDialog alertDialog = new MaterialAlertDialogBuilder(Map.this)
                .setTitle("Sign Out")
                .setPositiveButton("Sign Out", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(Map.this, LogIn.class);
                        startActivity(intent);
                        finish();


                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();
        alertDialog.show();



    }

    public void add_quiz(View view) {

        view1 = LayoutInflater.from(Map.this).inflate(R.layout.add_quiz, null);
        AlertDialog alertDialog = new MaterialAlertDialogBuilder(Map.this)
                .setTitle("Add Task")
                .setView(view1)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {



                    }
                }).setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();
        alertDialog.show();

    }

//    public void edittext(View view) {
//        View view2 = LayoutInflater.from(Map.this).inflate(R.layout.input_text_dialog_layout, null);
//        TextInputEditText editText = view2.findViewById(R.id.edittext);
//        TextView task_text = view1.findViewById(R.id.Task_text_View);
//        AlertDialog alertDialog = new MaterialAlertDialogBuilder(Map.this)
//                .setTitle("Title")
//                .setView(view2)
//                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Task_text = String.valueOf(editText.getText());
//                        task_text.setText(Task_text);
//                        dialogInterface.dismiss();
//                    }
//                }).setNegativeButton("Close", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                    }
//                }).create();
//        alertDialog.show();
//    }
}