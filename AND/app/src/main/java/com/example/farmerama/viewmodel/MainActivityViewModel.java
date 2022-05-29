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

public class MainActivityViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private BarnRepository barnRepository;
    private AreaRepository areaRepository;
    private SharedPreferences loginPreferences;
    private SharedPreferences notificationPreferences;
    private ExceededLogsRepository exceededLogsRepository;
    private boolean logged;

    public MainActivityViewModel(Application application) {
        super(application);
        loginPreferences = application.getSharedPreferences("Login", Context.MODE_PRIVATE);
        notificationPreferences = application.getSharedPreferences("Notification", Context.MODE_PRIVATE);
        userRepository = UserRepository.getInstance(application);
        barnRepository = BarnRepository.getInstance(application);
        exceededLogsRepository = ExceededLogsRepository.getInstance(application);
        areaRepository = AreaRepository.getInstance(application);
    }
    public void retrieveBarns() {
        barnRepository.retrieveBarns();
    }

    public void retrieveAreas(){areaRepository.retrieveAreas();}

    public void retrieveEmployees() {
        //userRepository.retrieveAllEmployees();
    }

    public LiveData<User> getLoggedInUser() {
        return userRepository.getLoggedInUser();
    }

    public void logOut() {
        userRepository.logOut();
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
        return exceededLogsRepository.getLatestLogs();
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
