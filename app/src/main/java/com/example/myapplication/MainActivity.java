package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity  {




    private FirebaseAuth auth;
    Button log;

    Button guest;

    FirebaseUser mUser;
    Button play;
    Button reg;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            mUser = extras.getParcelable("auth");
        }


        auth = FirebaseAuth.getInstance();

        reg = (Button) findViewById(R.id.reg);

        log = (Button) findViewById(R.id.login);

        play = (Button) findViewById(R.id.button);

        guest = (Button) findViewById(R.id.guest);


        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g = new Intent(MainActivity.this, Map.class);
                startActivity(g);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser != null) {
                    Intent intentL = new Intent(MainActivity.this, Map.class);
                    startActivity(intentL);
                }else {
                    Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_SHORT).show();
                }
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentL = new Intent(MainActivity.this, LogIn.class);
                startActivity(intentL);
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentL = new Intent(MainActivity.this, Registration.class);
                startActivity(intentL);
            }
        });
    }




}
