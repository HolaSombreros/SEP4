package com.example.farmerama.data.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.farmerama.MainActivity;
import com.example.farmerama.R;
import com.example.farmerama.data.model.ExceededLog;
import com.example.farmerama.data.model.response.LogResponse;
import com.example.farmerama.data.network.ServiceGenerator;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class NotificationWorker extends Worker {

    private PendingIntent resultPendingIntent;

    /**
     * A notification channel is created
     * The intent is set to open the main activity
     * @param context
     * @param workerParams
     */

    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        NotificationChannel channel = new NotificationChannel("22", "thresholdNotification", NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("Channel for the notification regarding exceeding thresholds");
        NotificationManager notificationManager = getApplicationContext().getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
        stackBuilder.addNextIntentWithParentStack(resultIntent);
        resultPendingIntent =
                stackBuilder.getPendingIntent(0,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
    }

    /**
     * API is called to retrieve the latest logs
     * For each log, a notification is published
     * @return
     */
    @NonNull
    @Override
    public Result doWork() {
        Call<List<LogResponse>> call = ServiceGenerator.getThresholdsApi().getLatestLogs();
        try {
            Response<List<LogResponse>> response = call.execute();
            for (int i = 0; i<response.body().size(); i++) {
                publishNotification(response.body().get(i).getLog(), i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Result.success();
    }

    private void publishNotification(ExceededLog log, int id) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "22")
                .setSmallIcon(R.mipmap.application_launcher)
                .setContentTitle("Measurement out of the thresholds")
                .setContentText(String.format("Exceeded %s in area %s", log.getMeasurementType(), log.getAreaName()))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setGroup("Exceeded Measurements")
                .setContentIntent(resultPendingIntent)
                .setChannelId("22");

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(id, builder.build());
    }
}