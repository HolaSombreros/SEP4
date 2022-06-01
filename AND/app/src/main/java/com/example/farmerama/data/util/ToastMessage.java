package com.example.farmerama.data.util;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ToastMessage {

    /**
     * Used for broadcasting the error message no matter where it is set
     */

    private static MutableLiveData<String> toast = new MutableLiveData<>();

    public static LiveData<String> getToastMessage() {
        return toast;
    }

    public static void setToastMessage(String string) {
        toast.setValue(string);
    }
}
