package com.example.farmerama.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.farmerama.R;
import com.example.farmerama.data.model.Area;
import com.example.farmerama.data.model.LogObj;
import com.example.farmerama.data.model.User;
import com.example.farmerama.data.repository.AreaRepository;
import com.example.farmerama.data.repository.ThresholdRepository;
import com.example.farmerama.data.repository.UserRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private SharedPreferences sharedPreferences;
    private ThresholdRepository thresholdRepository;

    public MainActivityViewModel(Application application) {
        super(application);
        sharedPreferences = application.getSharedPreferences("Login", Context.MODE_PRIVATE);
        userRepository = UserRepository.getInstance();
        thresholdRepository = ThresholdRepository.getInstance();
    }

    public LiveData<User> getLoggedInUser() {
        return userRepository.getLoggedInUser();
    }

    public void logOut() {
        userRepository.logOut();
    }

    public void saveLoggedInUser(User user) {
        sharedPreferences.edit().putString("userEmail", user.getEmail()).apply();
        sharedPreferences.edit().putString("userPassword", user.getPassword()).apply();
    }

    public void removeLoggedInUser() {
        sharedPreferences.edit().putString("userEmail", "null").apply();
        sharedPreferences.edit().putString("userPassword", "null").apply();
    }


    public LiveData<List<LogObj>> getTodayLogs() {
        return thresholdRepository.getLatestLogs();
    }
}
