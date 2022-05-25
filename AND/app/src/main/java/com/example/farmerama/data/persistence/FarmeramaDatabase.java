package com.example.farmerama.data.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.farmerama.data.model.Area;
import com.example.farmerama.data.model.Measurement;
import com.example.farmerama.data.model.Threshold;
import com.example.farmerama.data.model.User;

@Database(entities = {Area.class, Threshold.class, Measurement.class, User.class}, version = 2)
public abstract class FarmeramaDatabase extends RoomDatabase {

    private static FarmeramaDatabase instance;
    public abstract AreaDAO areaDAO();
    public abstract MeasurementDAO measurementDAO();
    public abstract ThresholdDAO thresholdDAO();
    public abstract UserDAO userDAO();

    public static synchronized FarmeramaDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            FarmeramaDatabase.class, "farmerama_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
