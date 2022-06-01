package com.example.farmerama.data.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.farmerama.data.model.Area;
import com.example.farmerama.data.model.Barn;
import com.example.farmerama.data.model.ExceededLog;
import com.example.farmerama.data.model.Measurement;
import com.example.farmerama.data.model.Threshold;
import com.example.farmerama.data.model.ThresholdModification;
import com.example.farmerama.data.model.User;

import com.example.farmerama.data.util.Converters;
import com.example.farmerama.data.util.MeasurementConverter;

@Database(entities = {Area.class, Threshold.class, Measurement.class, User.class, Barn.class, ExceededLog.class, ThresholdModification.class}, version = 23)
@TypeConverters({Converters.class,MeasurementConverter.class})
public abstract class FarmeramaDatabase extends RoomDatabase {

    private static FarmeramaDatabase instance;
    public abstract IAreaDAO areaDAO();
    public abstract IMeasurementDAO measurementDAO();
    public abstract IThresholdDAO thresholdDAO();
    public abstract IUserDAO userDAO();
    public abstract IBarnDAO barnDAO();
    public abstract IExceededLogDAO exceededLogDAO();
    public abstract IThresholdModificationDAO thresholdModificationDAO();

    public static FarmeramaDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            FarmeramaDatabase.class, "farmerama_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
