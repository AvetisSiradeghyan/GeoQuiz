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



        totalQuestionsTextView.setText("Total questions : "+totalQuestion);

        loadNewQuestion();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        if(score > totalQuestion*0.60){
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ score+" out of "+ totalQuestion)
                .setCancelable(false)
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

/*
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.common.SignInButton;

import java.util.ArrayList;

public class Quiz extends AppCompatActivity{

    RelativeLayout main_layout;

    TextView totalQuestionsTextView;

    TextView questionsTextView;

    Button ansAButton;

    Button ansBButton;

    Button ansCButton;

    Button ansDButton;

    Button okButton;

    int totalQuestions = QuizAns.questions.length;

    int currentQuestionIndex = 0;

    public static boolean compl = false;
    public static int score = 60;

    String selectedAnswer = "";
    Button selectedButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        totalQuestionsTextView = findViewById(R.id.total_questions);
        questionsTextView = findViewById(R.id.question);

        ansAButton = findViewById(R.id.ans_a);
        ansBButton = findViewById(R.id.ans_b);
        ansCButton = findViewById(R.id.ans_c);
        ansDButton = findViewById(R.id.ans_d);
        ArrayList<Button> Buttons = new ArrayList<>();

        Buttons.add(ansAButton);
        Buttons.add(ansBButton);
        Buttons.add(ansCButton);
        Buttons.add(ansDButton);
        main_layout = findViewById(R.id.main);
        okButton = findViewById(R.id.ok);


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
                if(selectedAnswer != ""){
            if(selectedAnswer.equals(QuizAns.answers[currentQuestionIndex])){
                score++;
                selectedButton.setBackgroundColor(Color.GREEN);

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
                            loadNewQuestions();
                        }
                        }, 600);
            }
        }    }
        });






        ansAButton.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
            selectedAnswer  = ansAButton.getText().toString();
            selectedButton = ansAButton;
            ansAButton.setBackgroundColor(Color.GRAY);
            ansBButton.setBackgroundColor(Color.WHITE);
            ansCButton.setBackgroundColor(Color.WHITE);
            ansDButton.setBackgroundColor(Color.WHITE);    }
        });

        ansBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer  = ansBButton.getText().toString();
                selectedButton = ansBButton;
                ansBButton.setBackgroundColor(Color.GRAY);
                ansAButton.setBackgroundColor(Color.WHITE);
                ansCButton.setBackgroundColor(Color.WHITE);
                ansDButton.setBackgroundColor(Color.WHITE);    }
        });

        ansCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer  = ansCButton.getText().toString();
                selectedButton = ansCButton;
                ansCButton.setBackgroundColor(Color.GRAY);
                ansBButton.setBackgroundColor(Color.WHITE);
                ansAButton.setBackgroundColor(Color.WHITE);
                ansDButton.setBackgroundColor(Color.WHITE);    }
        });

        ansDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer  = ansDButton.getText().toString();
                selectedButton = ansDButton;
                ansDButton.setBackgroundColor(Color.GRAY);
                ansBButton.setBackgroundColor(Color.WHITE);
                ansCButton.setBackgroundColor(Color.WHITE);
                ansAButton.setBackgroundColor(Color.WHITE);    }
        });



        totalQuestionsTextView.setText("Total questions " + totalQuestions);

        loadNewQuestions();





    }

    private void loadNewQuestions() {
        if (currentQuestionIndex == totalQuestions ) {

        }else {
            okButton.setBackgroundColor(Color.TRANSPARENT);
        }



        if (currentQuestionIndex == totalQuestions){
            okButton.setEnabled(false);


            return;
        }

        questionsTextView.setText(QuizAns.questions[currentQuestionIndex]);
        ansAButton.setText(QuizAns.choices[currentQuestionIndex][0]);
        ansBButton.setText(QuizAns.choices[currentQuestionIndex][1]);
        ansCButton.setText(QuizAns.choices[currentQuestionIndex][2]);
        ansDButton.setText(QuizAns.choices[currentQuestionIndex][3]);
    }



    }



}*/