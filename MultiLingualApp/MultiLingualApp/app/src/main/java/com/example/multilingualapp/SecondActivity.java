package com.example.multilingualapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        updateTexts();
    }

    private void updateTexts() {
        TextView textView = findViewById(R.id.text_view_second);

        // Get the saved language preference
        String savedLanguage = getSavedLanguagePreference();

        // Set the locale based on the saved language
        setLocale(this, savedLanguage);

        // Update the text based on the saved language
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = configuration.getLocales().get(0);

        // Update the text based on the current locale
        if (locale.getLanguage().equals("zu")) {
            textView.setText(R.string.zulu_second_page);
        } else if (locale.getLanguage().equals("af")) {
            textView.setText(R.string.afrikaans_second_page);
        } else {
            textView.setText(R.string.english_second_page);
        }
    }
    public static void setLocale(Context context, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);

        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    private String getSavedLanguagePreference() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        return prefs.getString("language_preference", "en");
    }
}
