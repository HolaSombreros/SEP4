package com.example.farmerama.data.repository;

public class SuccessResponse {
    private boolean isSuccess;

    public SuccessResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
