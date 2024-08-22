package com.example.lokale_assigment.model;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class Converters {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static String fromPrimaryDetails(Job.PrimaryDetails primaryDetails) {
        if (primaryDetails == null) {
            return "NOT DEFINED !!";
        }
        return gson.toJson(primaryDetails);
    }

    @TypeConverter
    public static Job.PrimaryDetails toPrimaryDetails(String data) {
        if (data == null || data.equals("NOT DEFINED !!")) {
            return new Job.PrimaryDetails("UNKNOWN PLACE", "UNKNOWN SALARY");
        }
        Type type = new TypeToken<Job.PrimaryDetails>() {}.getType();
        return gson.fromJson(data, type);
    }

    @TypeConverter
    public static String fromContactPreference(Job.ContactPreference contactPreference) {
        if (contactPreference == null) {
            return "NOT DEFINED !!";
        }
        return gson.toJson(contactPreference);
    }

    @TypeConverter
    public static Job.ContactPreference toContactPreference(String data) {
        if (data == null || data.equals("NOT DEFINED !!")) {
            return new Job.ContactPreference("00:00", "00:00", "UNKNOWN");
        }
        Type type = new TypeToken<Job.ContactPreference>() {}.getType();
        return gson.fromJson(data, type);
    }
}
