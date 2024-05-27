package com.example.myapplication;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import static kotlinx.coroutines.time.TimeKt.delay;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class Quiz extends AppCompatActivity{

    TextView totalQuestionsTextView,liveText;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;
//    String id = UUID.randomUUID().toString();
//    int Position_BackUp;

//    ArrayList<Quiz_Model> Quiz_Model = new ArrayList<>();

    public static boolean compl;
    public static int compled = 0;
    public static int score=0;
    int score_visible=0;

    int totalQuestion = QuizAns.questions.length;
    static int currentQuestionIndex = -1;
    String selectedAnswer = "";
    Button selectedButton;
    String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        totalQuestionsTextView = findViewById(R.id.total_questions);
        questionTextView = findViewById(R.id.question);
        ArrayList<Button> Buttons = new ArrayList<>();
        ansA = findViewById(R.id.ans_a);
        ansB = findViewById(R.id.ans_b);
        ansC = findViewById(R.id.ans_c);
        ansD = findViewById(R.id.ans_d);
        Buttons.add(ansA);
        Buttons.add(ansB);
        Buttons.add(ansC);
        Buttons.add(ansD);
        submitBtn = findViewById(R.id.ok);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            title = extras.getString("title");
        }




        loadNewQuestion();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ansA.setClickable(false);
                ansB.setClickable(false);
                ansC.setClickable(false);
                ansD.setClickable(false);
                if(selectedAnswer != "") {
                    if (Objects.equals(title, "Vanadzor")){
                        if (selectedAnswer.equals(QuizAns.answers[currentQuestionIndex])) {
                            score++;
                            score_visible++;
                            selectedButton.setBackgroundColor(Color.GREEN);


                            new Handler().postDelayed(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            loadNewQuestion();
                                        }
                                    }, 600);

                        } else {

                            selectedButton.setBackgroundColor(Color.RED);
                            for (Button i : Buttons) {
                                if (i.getText().toString().equals(QuizAns.answers[currentQuestionIndex])) {
                                    i.setBackgroundColor(Color.GREEN);
                                }
                            }
                            new Handler().postDelayed(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            loadNewQuestion();
                                        }
                                    }, 600);

                        }
                    }else if (Objects.equals(title, "Dilijan")){
                        if (selectedAnswer.equals(QuizAnsDilijan.answers[currentQuestionIndex])) {
                            score++;
                            score_visible++;
                            selectedButton.setBackgroundColor(Color.GREEN);


                            new Handler().postDelayed(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            loadNewQuestion();
                                        }
                                    }, 600);

                        } else {

                            selectedButton.setBackgroundColor(Color.RED);
                            for (Button i : Buttons) {
                                if (i.getText().toString().equals(QuizAnsDilijan.answers[currentQuestionIndex])) {
                                    i.setBackgroundColor(Color.GREEN);
                                }
                            }
                            new Handler().postDelayed(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            loadNewQuestion();
                                        }
                                    }, 600);

                        }
                    }
                }
            }
        });
        ansA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer  = ansA.getText().toString();
                selectedButton = ansA;
                ansA.setBackgroundColor(Color.GRAY);
                ansB.setBackgroundColor(Color.TRANSPARENT);
                ansC.setBackgroundColor(Color.TRANSPARENT);
                ansD.setBackgroundColor(Color.TRANSPARENT);
            }
        });
        ansB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer  = ansB.getText().toString();
                selectedButton = ansB;
                ansB.setBackgroundColor(Color.GRAY);
                ansA.setBackgroundColor(Color.TRANSPARENT);
                ansC.setBackgroundColor(Color.TRANSPARENT);
                ansD.setBackgroundColor(Color.TRANSPARENT);
            }
        });
        ansC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer  = ansC.getText().toString();
                selectedButton = ansC;
                ansC.setBackgroundColor(Color.GRAY);
                ansA.setBackgroundColor(Color.TRANSPARENT);
                ansB.setBackgroundColor(Color.TRANSPARENT);
                ansD.setBackgroundColor(Color.TRANSPARENT);
            }
        });
        ansD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer  = ansD.getText().toString();
                selectedButton = ansD;
                ansD.setBackgroundColor(Color.GRAY);
                ansA.setBackgroundColor(Color.TRANSPARENT);
                ansB.setBackgroundColor(Color.TRANSPARENT);
                ansC.setBackgroundColor(Color.TRANSPARENT);
            }
        });
    }
    void loadNewQuestion(){

        ansA.setClickable(true);
        ansB.setClickable(true);
        ansC.setClickable(true);
        ansD.setClickable(true);

        selectedAnswer = "";
        ansA.setBackgroundColor(Color.TRANSPARENT);
        ansB.setBackgroundColor(Color.TRANSPARENT);
        ansC.setBackgroundColor(Color.TRANSPARENT);
        ansD.setBackgroundColor(Color.TRANSPARENT);
        currentQuestionIndex++;

        if(currentQuestionIndex == totalQuestion ){
            finishQuiz();

            return;
        }


        if (Objects.equals(title, "Vanadzor")) {
            questionTextView.setText(QuizAns.questions[currentQuestionIndex]);
            ansA.setText(QuizAns.choices[currentQuestionIndex][0]);
            ansB.setText(QuizAns.choices[currentQuestionIndex][1]);
            ansC.setText(QuizAns.choices[currentQuestionIndex][2]);
            ansD.setText(QuizAns.choices[currentQuestionIndex][3]);
        } else if (Objects.equals(title, "Dilijan")) {
            questionTextView.setText(QuizAns.questions[currentQuestionIndex]);
            ansA.setText(QuizAnsDilijan.choices[currentQuestionIndex][0]);
            ansB.setText(QuizAnsDilijan.choices[currentQuestionIndex][1]);
            ansC.setText(QuizAnsDilijan.choices[currentQuestionIndex][2]);
            ansD.setText(QuizAnsDilijan.choices[currentQuestionIndex][3]);
        }
    }

    void finishQuiz(){
        compled++;
        compl = true;
        String passStatus = "";
        if(score > totalQuestion*0.50){
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ score_visible+" out of "+ totalQuestion)
                .setCancelable(true)
                .show();
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        loadNewQuestion();
                    }
                }, 600);
        startActivity(new Intent(Quiz.this, Map.class));




    }

//    public void finishfb(View view) {
//        if (score == 0){
//
//        String scorefb = String.valueOf(score);
//        String compledfb = String.valueOf(compled);
//
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//
//            DocumentReference docRef = db.collection("yourCollection").document(id);
//
//            // Delete the document
//            docRef.delete()
//                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()) {
//                                Log.d(TAG, "DocumentSnapshot successfully deleted!");
//                            } else {
//                                Log.w(TAG, "Error deleting document", task.getException());
//                            }
//                        }
//                    });
//
//            HashMap<String, Object> hashMap = new HashMap<>();
//            hashMap.put("Score", scorefb);
//            hashMap.put("Completed", compledfb);
//            hashMap.put("UserId", user.getUid());
//            db.collection("daysModel").document(id).set(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
//                @Override
//                public void onSuccess(Void unused) {
//                    Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();
//                }
//
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            //db.collection("daysModel").document("daysModelId").collection("tasks").add(hashMap);
//        }
//    }else {
//            Change_Quiz_In_Model();
//        }
//
//    }
//
//
//    private void Change_Quiz_In_Model() {
//        String idNew = UUID.randomUUID().toString();
//
//
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("daysModel").document(id).collection("taskModels").document(Quiz_Model.get(Position_BackUp).DocId).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//
//                    Quiz_Model.set(Position_BackUp, new Quiz_Model(score, compled, idNew));
//
//                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                    if (user != null) {
//                        HashMap<String, Object> hashMap = new HashMap<>();
//                        hashMap.put("Score", String.valueOf(score));
//                        hashMap.put("Completed", String.valueOf(compled));
//                        hashMap.put("DocID", idNew);
//                        hashMap.put("userId", user.getUid());
//                        db.collection("daysModel").document(Quiz_Model.get(Position_BackUp).DocId).set(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void unused) {
//                                Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//                        //db.collection("daysModel").document("daysModelId").collection("taskModels").add(hashMap);
//                    }
//
//                }
//
//
//
//        });
//
//
//    }
}

