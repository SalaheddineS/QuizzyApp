package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class QuizzActivity extends AppCompatActivity implements View.OnClickListener {

    androidx.appcompat.widget.AppCompatButton questionnumb;
    TextView question;
    Button ansA, ansB, ansC, ansD;
    int score = 0;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizz_activity);
        questionnumb = findViewById(R.id.questnumb);
        question = findViewById(R.id.question);
        ansA = findViewById(R.id.rep1);
        ansB = findViewById(R.id.rep2);
        ansC = findViewById(R.id.rep3);
        ansD = findViewById(R.id.rep4);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);

        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;
        selectedAnswer = clickedButton.getText().toString();

        if (selectedAnswer.equals(QuestionAnswer.CorrectAnswers[currentQuestionIndex])) {
            clickedButton.setBackgroundResource(R.drawable.greencorners);
            score = score + 100;

        } else {
            clickedButton.setBackgroundResource(R.drawable.redcorners);

        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                clickedButton.setBackgroundResource(R.drawable.roundcorners);

                currentQuestionIndex++;
                if (currentQuestionIndex < QuestionAnswer.Questions.length) {
                    loadNewQuestion();
                } else {
                    Intent intent = new Intent(QuizzActivity.this, ResultActivity.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                }
            }
        }, 1000);





    }

    void loadNewQuestion() {
        questionnumb.setText(String.valueOf(currentQuestionIndex+1));
        question.setText(QuestionAnswer.Questions[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.Choices[currentQuestionIndex][3]);
        ansB.setText(QuestionAnswer.Choices[currentQuestionIndex][2]);
        ansC.setText(QuestionAnswer.Choices[currentQuestionIndex][1]);
        ansD.setText(QuestionAnswer.Choices[currentQuestionIndex][0]);
    }
}