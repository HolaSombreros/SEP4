package com.example.farmerama.data.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.farmerama.data.repository.ThresholdRepository;

public class NotificationWorker extends Worker {

    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    public Result doWork() {
        ThresholdRepository.getInstance().retrieveTodayLogs();
        return Result.success();
    }
}
