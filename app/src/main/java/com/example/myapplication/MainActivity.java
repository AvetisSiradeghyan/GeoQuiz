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

public class MainActivity extends AppCompatActivity  {


    Button guest;
    private FirebaseAuth auth;
    Button log;

    private boolean user = LogIn.user;
    Button play;
    Button reg;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guest = (Button) findViewById(R.id.guest);

        auth = FirebaseAuth.getInstance();

        reg = (Button) findViewById(R.id.reg);

        log = (Button) findViewById(R.id.login);

        play = (Button) findViewById(R.id.button);

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Menu.class));
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user == true) {
                    Intent intentL = new Intent(MainActivity.this, Menu.class);
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
