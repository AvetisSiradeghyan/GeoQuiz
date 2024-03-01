package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity implements View.OnClickListener {

    ImageButton map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        map = (ImageButton) findViewById(R.id.imageButton);

        map.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

            Intent intentL = new Intent(this, MapSp.class);
            startActivity(intentL);



    }
}