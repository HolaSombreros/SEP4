package com.example.farmerama.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;

import com.example.farmerama.data.model.User;

public class MainActivityViewModel extends FactoryViewModel {

    private SharedPreferences loginPreferences;
    private SharedPreferences notificationPreferences;
    private boolean logged;

    public MainActivityViewModel(Application application) {
        super(application);
        loginPreferences = application.getSharedPreferences("Login", Context.MODE_PRIVATE);
        notificationPreferences = application.getSharedPreferences("Notification", Context.MODE_PRIVATE);
    }
    public void retrieveBarns() {
        getBarnRepository().retrieveBarns();
    }

    public void retrieveAreas(){getAreaRepository().retrieveAreas();}

    public LiveData<User> getLoggedInUser() {
        return getUserRepository().getLoggedInUser();
    }

    public void logOut() {
        getUserRepository().logOut();
        loginPreferences.edit().putString("userEmail", "null").apply();
        loginPreferences.edit().putString("userPassword", "null").apply();
    }

    public void saveLoggedInUser(User user) {
        loginPreferences.edit().putString("userEmail", user.getEmail()).apply();
        loginPreferences.edit().putString("userPassword", user.getPassword()).apply();
    }

    public void setLogged(boolean b) {
        logged = b;
    }

    public boolean isLogged() {
        return logged;
    }

    public boolean isGettingNotifications() {
        return notificationPreferences.getBoolean("notification", false);
    }
}
