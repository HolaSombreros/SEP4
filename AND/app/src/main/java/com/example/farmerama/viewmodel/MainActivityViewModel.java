package com.example.farmerama.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.farmerama.data.model.ExceededLog;
import com.example.farmerama.data.model.User;
import com.example.farmerama.data.repository.AreaRepository;
import com.example.farmerama.data.repository.BarnRepository;
import com.example.farmerama.data.repository.ExceededLogsRepository;
import com.example.farmerama.data.repository.ThresholdRepository;
import com.example.farmerama.data.repository.UserRepository;

import java.util.List;

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
    }

    public void saveLoggedInUser(User user) {
        loginPreferences.edit().putString("userEmail", user.getEmail()).apply();
        loginPreferences.edit().putString("userPassword", user.getPassword()).apply();
    }

    public void removeLoggedInUser() {
        loginPreferences.edit().putString("userEmail", "null").apply();
        loginPreferences.edit().putString("userPassword", "null").apply();
    }


    public LiveData<List<ExceededLog>> getTodayLogs() {
        return getExceededLogsRepository().getLatestLogs();
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
