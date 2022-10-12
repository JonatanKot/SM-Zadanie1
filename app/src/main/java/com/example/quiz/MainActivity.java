package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;

    private Question[] questions = new Question[] {
            new Question(R.string.Q1, true),
            new Question(R.string.Q2, true),
            new Question(R.string.Q3, true),
            new Question(R.string.Q4, false),
            new Question(R.string.Q5, true),
    };

    private int current_index = 0;

    private void setNextQuestion(){
        questionTextView.setText(questions[current_index].getQuestionId());
    }

    private void checkAnswerCorrectness(boolean userAnswer){
        boolean correctAnswer = questions[current_index].isTrueAnswer();
        int resultMessageId = 0;
        if(userAnswer == correctAnswer){
            resultMessageId = R.string.correct_answer;
        }
        else{
            resultMessageId = R.string.incorrect_answer;
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.button_true);
        falseButton = findViewById(R.id.button_false);
        nextButton = findViewById(R.id.button_next);
        questionTextView = findViewById(R.id.question_text_view);

        trueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                checkAnswerCorrectness(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                checkAnswerCorrectness(false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                current_index = (current_index + 1) % questions.length;
                setNextQuestion();
            }
        });
        setNextQuestion();
    }
}