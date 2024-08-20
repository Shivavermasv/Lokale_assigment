package com.example.lokale_assigment.Databse;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import android.content.Context;

import com.example.lokale_assigment.dao.JobsDao;
import com.example.lokale_assigment.model.Converters;
import com.example.lokale_assigment.model.Job;

@Database(entities = {Job.class}, version = 2, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {

    public abstract JobsDao jobDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "job_database")
                            .allowMainThreadQueries() // Not recommended for production apps
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
