package com.example.farmerama.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;

import com.example.farmerama.R;

public class IntroViewModel extends AndroidViewModel {
    private String prevStarted = "yes";

    public IntroViewModel(@NonNull Application application) {
        super(application);
    }

    public boolean isSeen() {
        SharedPreferences sharedpreferences = getApplication().getSharedPreferences(getApplication().getString(R.string.app_name), Context.MODE_PRIVATE);
        if (!sharedpreferences.getBoolean(prevStarted, false)) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(prevStarted, Boolean.TRUE);
            editor.apply();
            return false;
        } else {
            return true;
        }
    }

}
