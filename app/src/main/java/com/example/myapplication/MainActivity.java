package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button log;

    Button play;
    Button reg;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reg = (Button) findViewById(R.id.reg);

        log = (Button) findViewById(R.id.login);

        play = (Button) findViewById(R.id.button);

        play.setOnClickListener(this);

        log.setOnClickListener(this);

        reg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intentL = new Intent(this, Registration.class);
        startActivity(intentL);

    }

//    @Override
//        public void onClick(View view) {
//            Intent intentL = new Intent(this, LogIn.class);
//            startActivity(intentL);
//}


//    @Override
//    public void onClick(View view) {
//        Intent intentL = new Intent(this, Menu.class);
//        startActivity(intentL);
//
//    }


}
