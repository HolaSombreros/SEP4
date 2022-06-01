package com.example.farmerama.data.util;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivityChecker {
    private Application application;

    public ConnectivityChecker(Application application) {
        this.application = application;
    }

    /**
     * Checks if the user is online or not
     * It is used in the Repositories to check for retrieval of data
     * @return
     */
    public boolean isOnlineMode() {
        ConnectivityManager connectivityManager = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
