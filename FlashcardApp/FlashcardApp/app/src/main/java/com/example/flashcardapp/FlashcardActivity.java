package com.example.flashcardapp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FlashcardActivity extends AppCompatActivity {

    private FlashcardDatabaseHelper flashcardDBHelper;
    private TextView questionTextView, answerTextView;
    private Button deleteButton;
    private int flashcardId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);

        flashcardDBHelper = new FlashcardDatabaseHelper(this);
        questionTextView = findViewById(R.id.questionTextView);
        answerTextView = findViewById(R.id.answerTextView);
        deleteButton = findViewById(R.id.deleteButton);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            flashcardId = extras.getInt("flashcardId");
            String question = extras.getString("question");
            String answer = extras.getString("answer");

            questionTextView.setText(question);
            answerTextView.setText(answer);
        }

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardDBHelper.deleteFlashcard(flashcardId);
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }
}
