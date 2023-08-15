package com.example.flashcardapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private FlashcardDatabaseHelper flashcardDBHelper;
    private Button addButton, viewButton;
    private EditText questionEditText, answerEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDBHelper = new FlashcardDatabaseHelper(this);
        addButton = findViewById(R.id.addButton);
        viewButton = findViewById(R.id.viewButton);
        questionEditText = findViewById(R.id.questionEditText);
        answerEditText = findViewById(R.id.answerEditText);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = questionEditText.getText().toString();
                String answer = answerEditText.getText().toString();
                flashcardDBHelper.addFlashcard(question, answer);
                Toast.makeText(MainActivity.this, "Flashcard added", Toast.LENGTH_SHORT).show();
                questionEditText.setText("");
                answerEditText.setText("");
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewFlashcardsActivity.class);
                startActivity(intent);
            }
        });
    }
}
