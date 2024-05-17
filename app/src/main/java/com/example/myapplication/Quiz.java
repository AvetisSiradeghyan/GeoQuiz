package com.example.myapplication;
import static kotlinx.coroutines.time.TimeKt.delay;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Quiz extends AppCompatActivity{

    TextView totalQuestionsTextView,liveText;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    int score=0;

    int totalQuestion = QuizAns.questions.length;
    static int currentQuestionIndex = -1;
    String selectedAnswer = "";
    Button selectedButton;

//    String town;
//
//     String choices [] = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

//        Bundle extras = getIntent().getExtras();
//        if(extras != null){
//            town = extras.getString("title");
//        }
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if(user != null){
//
//
//            FirebaseFirestore.getInstance().collection("Quiz").whereEqualTo("Town", town)
//                    .get()
//                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                        @Override
//                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                            for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots){
//                                choices = queryDocumentSnapshot.get("Choices");
//
//                            }
//
//
//
//                        }
//                    });
//        }

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



        totalQuestionsTextView.setText("Total questions : "+totalQuestion);

        loadNewQuestion();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ansA.setClickable(false);
                ansB.setClickable(false);
                ansC.setClickable(false);
                ansD.setClickable(false);
                if(selectedAnswer != ""){
                    if(selectedAnswer.equals(QuizAns.answers[currentQuestionIndex])){
                        score++;
                        selectedButton.setBackgroundColor(Color.GREEN);


                        new Handler().postDelayed(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        loadNewQuestion();
                                    }
                                }, 600);

                    }
                    else {

                        selectedButton.setBackgroundColor(Color.RED);
                        for(Button i:Buttons){
                            if(i.getText().toString().equals(QuizAns.answers[currentQuestionIndex])){
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

        questionTextView.setText(QuizAns.questions[currentQuestionIndex]);
        ansA.setText(QuizAns.choices[currentQuestionIndex][0]);
        ansB.setText(QuizAns.choices[currentQuestionIndex][1]);
        ansC.setText(QuizAns.choices[currentQuestionIndex][2]);
        ansD.setText(QuizAns.choices[currentQuestionIndex][3]);


    }

    void finishQuiz(){
        String passStatus = "";
        if(score > totalQuestion*0.50){
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ score+" out of "+ totalQuestion)
                .setCancelable(true)
                .show();
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Quiz.this, Map.class));

                    }
                }, 600);



    }
}

