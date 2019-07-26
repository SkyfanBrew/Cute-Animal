package com.tiutiunnyk.cuteanimals.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class PreferenceUtils {

    private SharedPreferences sharedPreferences;

    public PreferenceUtils(Context context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setIsCatsDataLoaded(boolean firstLoading) {
        sharedPreferences.edit().putBoolean("is_cats_loaded", firstLoading).apply();
    }

    public boolean isCatsLoaded() {
        return sharedPreferences.getBoolean("is_cats_loaded", false);
    }

    public void setIsDogsDataLoaded(boolean firstLoading) {
        sharedPreferences.edit().putBoolean("is_dogs_loaded", firstLoading).apply();
    }

    public boolean isDogsDataLoaded() {
        return sharedPreferences.getBoolean("is_dogs_loaded", false);
    }

}
