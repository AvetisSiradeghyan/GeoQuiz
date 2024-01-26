package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;




public class LogIn extends AppCompatActivity implements  View.OnClickListener{

    TextView reg1;

    Button log;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        

        reg1 = (TextView) findViewById(R.id.reg);

        reg1.setOnClickListener(this);


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
                Intent intentR = new Intent(this, Registration.class);
                startActivity(intentR);




    }
}