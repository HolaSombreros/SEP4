package com.example.farmerama.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.farmerama.data.model.User;
import com.example.farmerama.data.repository.UserRepository;

public class MainActivityViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private SharedPreferences sharedPreferences;

    public MainActivityViewModel(Application application) {
        super(application);
        sharedPreferences = application.getSharedPreferences("Login", Context.MODE_PRIVATE);
        userRepository = UserRepository.getInstance();
    }

    public LiveData<User> getLoggedInUser() {
        return userRepository.getLoggedInUser();
    }

    public void logOut() {
        userRepository.logOut();
    }

    public LiveData<Boolean> getGuest() {
        return userRepository.isGuest();
    }

    public void saveLoggedInUser(User user) {
        sharedPreferences.edit().putString("userEmail", user.getEmail()).apply();
        sharedPreferences.edit().putString("userPassword", user.getPassword()).apply();
    }

    public void removeLoggedInUser() {
        sharedPreferences.edit().putString("userEmail", "null").apply();
        sharedPreferences.edit().putString("userPassword", "null").apply();
    }
}
