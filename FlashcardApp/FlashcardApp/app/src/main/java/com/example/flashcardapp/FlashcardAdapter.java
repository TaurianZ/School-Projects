package com.example.flashcardapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FlashcardAdapter extends ArrayAdapter<Flashcard> {

    private Context context;
    private List<Flashcard> flashcards;

    public FlashcardAdapter(Context context, List<Flashcard> flashcards) {
        super(context, 0, flashcards);
        this.context = context;
        this.flashcards = flashcards;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.flashcard_item, parent, false);
        }

        Flashcard currentFlashcard = flashcards.get(position);

        TextView questionTextView = listItemView.findViewById(R.id.questionTextView);
        TextView answerTextView = listItemView.findViewById(R.id.answerTextView);

        questionTextView.setText(currentFlashcard.getQuestion());
        answerTextView.setText(currentFlashcard.getAnswer());

        return listItemView;
    }
}

