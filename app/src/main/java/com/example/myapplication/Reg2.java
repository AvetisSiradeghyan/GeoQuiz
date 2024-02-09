package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Reg2 extends AppCompatActivity implements  View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg2);

        Button next1;

        next1 = (Button) findViewById(R.id.next);


        next1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intentR2 = new Intent(this, Reg3.class);
        startActivity(intentR2);


    }
}