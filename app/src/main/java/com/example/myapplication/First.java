package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class First extends AppCompatActivity {

    int counter = 0;
    ProgressBar pb;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);




        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.back, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.logo, ScaleTypes.FIT));

        slideModels.add(new SlideModel(R.drawable.map, ScaleTypes.FIT));


        imageSlider.setImageList(slideModels, ScaleTypes.FIT);




    }



}