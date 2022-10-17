package com.example.quiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_CURRENT_INDEX = "currentIndex";
    public static final String KEY_EXTRA_ANSWER = "com.example.quiz.correctAnswer";
    public static final int REQUEST_CODE_PROMPT = 0;

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button hintButton;
    private TextView questionTextView;


    private Question[] questions = new Question[] {
            new Question(R.string.Q1, true),
            new Question(R.string.Q2, true),
            new Question(R.string.Q3, true),
            new Question(R.string.Q4, false),
            new Question(R.string.Q5, true),
    };

    private int current_index = 0;
    private boolean answerWasShown = false;

    private void setNextQuestion(){
        questionTextView.setText(questions[current_index].getQuestionId());
    }

    private void checkAnswerCorrectness(boolean userAnswer){
        boolean correctAnswer = questions[current_index].isTrueAnswer();
        int resultMessageId = 0;
        if(answerWasShown){
            resultMessageId = R.string.answer_was_shown;
        }else {
            if (userAnswer == correctAnswer) {
                resultMessageId = R.string.correct_answer;
            } else {
                resultMessageId = R.string.incorrect_answer;
            }
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Zadanie", "onCreate");

        if(savedInstanceState != null){
            current_index = savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }

        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.button_true);
        falseButton = findViewById(R.id.button_false);
        nextButton = findViewById(R.id.button_next);
        hintButton = findViewById(R.id.button_hint);
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
                answerWasShown = false;
                setNextQuestion();
            }
        });

        hintButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PromptActivity.class);
                boolean correctAnswer = questions[current_index].isTrueAnswer();
                intent.putExtra(KEY_EXTRA_ANSWER, correctAnswer);
                startActivityForResult(intent, REQUEST_CODE_PROMPT);
            }
        });
        setNextQuestion();
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {return;}
        if (requestCode == REQUEST_CODE_PROMPT){
            if (data == null) {return;}
            answerWasShown = data.getBooleanExtra(PromptActivity.KEY_EXTRA_ANSWER_SHOWN, false);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Zadanie", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Zadanie", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Zadanie", "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Zadanie", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Zadanie", "onResume");
    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d("Zadanie", "onSaveInstanceState");
        outState.putInt(KEY_CURRENT_INDEX, current_index);
    }
}