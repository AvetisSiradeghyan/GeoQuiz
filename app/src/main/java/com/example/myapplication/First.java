package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class First extends AppCompatActivity {

    int counter = 0;

    Button quiz_button;
    String title;
    ProgressBar pb;
    Button btn;
    TextView Title, Info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            title = extras.getString("title");
        }
        Info = findViewById(R.id.info_first);
        Title = findViewById(R.id.title_town);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            FirebaseFirestore.getInstance().collection("Quiz").whereEqualTo("Town", title)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots){
                                Info.setText(queryDocumentSnapshot.getString("Info"));
                                Title.setText(queryDocumentSnapshot.getString("Town"));
                            }



                        }
                    });
        }




        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();



        if (Objects.equals(title, "Վանաձոր")) {
            slideModels.clear();
            slideModels.add(new SlideModel(R.drawable.vanadzor1, ScaleTypes.FIT));
            slideModels.add(new SlideModel(R.drawable.vanadzor2, ScaleTypes.FIT));
            slideModels.add(new SlideModel(R.drawable.vanadzor3, ScaleTypes.FIT));
        } else if (Objects.equals(title, "Դիլիջան")) {
            slideModels.clear();
            slideModels.add(new SlideModel(R.drawable.dilijan1, ScaleTypes.FIT));
            slideModels.add(new SlideModel(R.drawable.dilijan2, ScaleTypes.FIT));
            slideModels.add(new SlideModel(R.drawable.dilijan3, ScaleTypes.FIT));
        }else if (Objects.equals(title, "Երևան")) {
            slideModels.clear();
            slideModels.add(new SlideModel(R.drawable.yerevan1, ScaleTypes.FIT));
            slideModels.add(new SlideModel(R.drawable.yerevan2, ScaleTypes.FIT));
            slideModels.add(new SlideModel(R.drawable.yerevan3, ScaleTypes.FIT));
        }else if (Objects.equals(title, "Գյումրի")) {
            slideModels.clear();
            slideModels.add(new SlideModel(R.drawable.gyumri1, ScaleTypes.FIT));
            slideModels.add(new SlideModel(R.drawable.gyumri2, ScaleTypes.FIT));
            slideModels.add(new SlideModel(R.drawable.gyumri3, ScaleTypes.FIT));
        }else if (Objects.equals(title, "Իջևան")) {
            slideModels.clear();
            slideModels.add(new SlideModel(R.drawable.ijevan1, ScaleTypes.FIT));
            slideModels.add(new SlideModel(R.drawable.ijevan2, ScaleTypes.FIT));
            slideModels.add(new SlideModel(R.drawable.ijevan3, ScaleTypes.FIT));
        }

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);


        quiz_button = findViewById(R.id.quiz_button);

        quiz_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Quiz.currentQuestionIndex = -1;
                    Intent i = new Intent(First.this, Quiz.class);
                    i.putExtra("title", title);
                    startActivity(i);

            }
        });


    }



}