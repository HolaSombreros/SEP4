package com.example.farmerama.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;

import com.example.farmerama.data.repository.UserRepository;

public class SettingsViewModel extends AndroidViewModel {

    private UserRepository repository;
    private SharedPreferences notificationPreferences;

    public SettingsViewModel(Application application) {
        super(application);
        repository = UserRepository.getInstance(application);
        notificationPreferences = application.getSharedPreferences("Notification", Context.MODE_PRIVATE);
    }

    public void removeLocalData() {
        repository.removeLocalData();
    }

    public void setGettingNotifications(boolean newValue) {
        notificationPreferences.edit().putBoolean("notification", newValue).apply();
    }
}
