package com.example.myapplication;

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

public class Quiz extends AppCompatActivity implements View.OnClickListener {

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
        main_layout = findViewById(R.id.main);
        okButton = findViewById(R.id.ok);


        ansAButton.setOnClickListener(this);
        ansBButton.setOnClickListener(this);
        ansCButton.setOnClickListener(this);
        ansDButton.setOnClickListener(this);
        okButton.setOnClickListener(this);

        totalQuestionsTextView.setText("Total questions " + totalQuestions);

        loadNewQuestions();

    }



    @SuppressLint("ResourceAsColor")
    public void onClick (View view){
        ansAButton.setBackgroundColor(Color.TRANSPARENT);
        ansBButton.setBackgroundColor(Color.TRANSPARENT);
        ansCButton.setBackgroundColor(Color.TRANSPARENT);
        ansDButton.setBackgroundColor(Color.TRANSPARENT);

        Button button = (Button)  view;
        if(button.getId() == R.id.ok){
            if (selectedAnswer.equals(QuizAns.answers[currentQuestionIndex])){
                score++;
                main_layout.setBackgroundColor(Color.GREEN);
                delay(300);

            }else {
                main_layout.setBackgroundColor(Color.RED);
                delay(300);
            }
            currentQuestionIndex++;
            loadNewQuestions();

        }else {
            selectedAnswer = button.getText().toString();
            button.setBackgroundColor(Color.YELLOW);
        }

    }

    private void loadNewQuestions() {
        if (currentQuestionIndex == totalQuestions ) {
            finishQuiz();
        }else {
            okButton.setBackgroundColor(Color.TRANSPARENT);
        }



        if (currentQuestionIndex == totalQuestions){
            okButton.setEnabled(false);
            finishQuiz();

            return;
        }

        questionsTextView.setText(QuizAns.questions[currentQuestionIndex]);
        ansAButton.setText(QuizAns.choices[currentQuestionIndex][0]);
        ansBButton.setText(QuizAns.choices[currentQuestionIndex][1]);
        ansCButton.setText(QuizAns.choices[currentQuestionIndex][2]);
        ansDButton.setText(QuizAns.choices[currentQuestionIndex][3]);
    }

    private  void finishQuiz () {



        if (score > totalQuestions * 0.6){
            compl = true;
            Intent intent = new Intent(Quiz.this, Map.class);
            startActivity(intent);

        }else {
            compl = false;
            Intent intent = new Intent(Quiz.this, Map.class);
            startActivity(intent);
        }



    }



    public void delay (int milliseconds){
        new Handler().postDelayed(new Runnable() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void run() {
                main_layout.setBackgroundColor(Color.TRANSPARENT);
                main_layout.setBackgroundColor(Color.TRANSPARENT);
            }
        }, milliseconds);
    }
}