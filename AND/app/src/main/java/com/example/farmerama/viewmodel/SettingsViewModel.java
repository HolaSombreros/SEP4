package com.example.farmerama.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class SettingsViewModel extends FactoryViewModel {

    private SharedPreferences notificationPreferences;

    public SettingsViewModel(Application application) {
        super(application);
        notificationPreferences = application.getSharedPreferences("Notification", Context.MODE_PRIVATE);
    }

    public void removeLocalData() {
        getUserRepository().removeLocalData();
    }

    public void setGettingNotifications(boolean newValue) {
        notificationPreferences.edit().putBoolean("notification", newValue).apply();
    }
}
