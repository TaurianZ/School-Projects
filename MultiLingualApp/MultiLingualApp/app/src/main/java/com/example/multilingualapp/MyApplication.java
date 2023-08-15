package com.example.multilingualapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;


import java.util.Locale;

public class MyApplication extends Application {
    private static final String LANG_PREF_KEY = "language_preference";

    @Override
    public void onCreate() {
        super.onCreate();
        updateLanguage(this);
    }

    public static void updateLanguage(Context context) {
        String savedLang = getSavedLanguage(context);
        updateLanguage(context, savedLang);
    }

    public static void updateLanguage(Context context, String lang) {
        Locale locale = getLocaleFromLanguage(lang);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);

        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        saveLanguage(context, lang);
    }

    private static Locale getLocaleFromLanguage(String lang) {
        if (lang.equals("zu")) {
            return new Locale("zu");
        } else if (lang.equals("af")) {
            return new Locale("af");
        } else {
            return Locale.ENGLISH;
        }
    }

    private static String getSavedLanguage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(LANG_PREF_KEY, "");
    }

    private static void saveLanguage(Context context, String lang) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(LANG_PREF_KEY, lang);
        editor.apply();
    }
}


