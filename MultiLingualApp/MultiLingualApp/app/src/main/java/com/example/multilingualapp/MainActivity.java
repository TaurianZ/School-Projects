package com.example.multilingualapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the button to go to the second activity
        Button button = findViewById(R.id.my_button);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });

        updateTexts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        // Handle item selection
        String selectedLanguage = "";
        switch (itemId) {
            case R.id.english:
                selectedLanguage = "en";
                break;
            case R.id.zulu:
                selectedLanguage = "zu";
                break;
            case R.id.afrikaans:
                selectedLanguage = "af";
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        if (!selectedLanguage.isEmpty()) {
            setLocale(selectedLanguage);
            saveLanguagePreference(selectedLanguage); // Save the language preference

            // Recreate the activity to apply the language change
            recreate();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void setLocale(String lang) {
        MyApplication.updateLanguage(this, lang);
    }

    private void saveLanguagePreference(String lang) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("language_preference", lang);
        editor.apply();
    }

    private String getSavedLanguagePreference() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getString("language_preference", "");
    }

    private void updateTexts() {
        TextView textView = findViewById(R.id.text_view);
        Button button = findViewById(R.id.my_button);

        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = configuration.locale;

        String savedLang = getSavedLanguagePreference(); // Get the saved language preference

        // Update the text based on the current locale or saved language preference
        if (locale.getLanguage().equals("zu") || savedLang.equals("zu")) {
            textView.setText(resources.getString(R.string.zulu_text));
            button.setText(resources.getString(R.string.zulu_button_text));
        } else if (locale.getLanguage().equals("af") || savedLang.equals("af")) {
            textView.setText(resources.getString(R.string.afrikaans_text));
            button.setText(resources.getString(R.string.afrikaans_button_text));
        } else {
            textView.setText(resources.getString(R.string.english_text));
            button.setText(resources.getString(R.string.english_button_text));
        }
    }
}
