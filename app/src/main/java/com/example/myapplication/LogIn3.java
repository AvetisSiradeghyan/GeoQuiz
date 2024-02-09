package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogIn3 extends AppCompatActivity implements View.OnClickListener{

    Button next1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in3);


        next1 = (Button) findViewById(R.id.next);


        next1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intentL2 = new Intent(this, MainActivity.class);
        startActivity(intentL2);


    }
}