package com.example.flashcardapp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ViewFlashcardsActivity extends AppCompatActivity {

    private FlashcardDatabaseHelper flashcardDBHelper;
    private ListView flashcardsListView;
    private List<Flashcard> flashcards;
    private FlashcardAdapter flashcardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flashcards);

        flashcardDBHelper = new FlashcardDatabaseHelper(this);
        flashcardsListView = findViewById(R.id.flashcardsListView);

        flashcards = flashcardDBHelper.getAllFlashcards();
        flashcardAdapter = new FlashcardAdapter(this, flashcards);
        flashcardsListView.setAdapter(flashcardAdapter);

        flashcardsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Flashcard flashcard = flashcards.get(position);
                Intent intent = new Intent(ViewFlashcardsActivity.this, FlashcardActivity.class);
                intent.putExtra("flashcardId", flashcard.getId());
                intent.putExtra("question", flashcard.getQuestion());
                intent.putExtra("answer", flashcard.getAnswer());
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            refreshFlashcards();
        }
    }

    private void refreshFlashcards() {
        flashcards = flashcardDBHelper.getAllFlashcards();
        flashcardAdapter.clear();
        flashcardAdapter.addAll(flashcards);
        flashcardAdapter.notifyDataSetChanged();
    }
}
