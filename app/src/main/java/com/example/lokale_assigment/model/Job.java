package com.example.lokale_assigment.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "jobs")
public class Job implements Serializable {
    public Job() {
        title = "TITLE";
    }

    @PrimaryKey
    @NonNull
    @SerializedName("title")
    private String title;



    @SerializedName("primary_details")
    private PrimaryDetails primaryDetails;

    @SerializedName("contact_preference")
    private ContactPreference contactPreference;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PrimaryDetails getPrimaryDetails() {
        return primaryDetails;
    }

    public void setPrimaryDetails(PrimaryDetails primaryDetails) {
        this.primaryDetails = primaryDetails;
    }

    public ContactPreference getContactPreference() {
        return contactPreference;
    }

    public void setContactPreference(ContactPreference contactPreference) {
        this.contactPreference = contactPreference;
    }

    public static class PrimaryDetails implements Serializable {
        @SerializedName("Place")
        private String place;

        @SerializedName("Salary")
        private String salary;

        public PrimaryDetails(String unknownPlace, String unknownSalary) {
            this.place = unknownPlace;
            this.salary = unknownSalary;
        }

        // Getters and Setters
        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }
    }

    public static class ContactPreference implements Serializable {
        @SerializedName("preferred_call_start_time")
        private String preferredCallStartTime;

        @SerializedName("preferred_call_end_time")
        private String preferredCallEndTime;

        @SerializedName("whatsapp_no")
        private String whatsappNo;

        public ContactPreference(String time, String time1, String unknown) {
            this.preferredCallStartTime = time;
            this.preferredCallEndTime = time1;
            this.whatsappNo = unknown;
        }

        // Getters and Setters
        public String getPreferredCallStartTime() {
            return preferredCallStartTime;
        }

        public void setPreferredCallStartTime(String preferredCallStartTime) {
            this.preferredCallStartTime = preferredCallStartTime;
        }

        public String getPreferredCallEndTime() {
            return preferredCallEndTime;
        }

        public void setPreferredCallEndTime(String preferredCallEndTime) {
            this.preferredCallEndTime = preferredCallEndTime;
        }

        public String getWhatsappNo() {
            return whatsappNo;
        }

        public void setWhatsappNo(String whatsappNo) {
            this.whatsappNo = whatsappNo;
        }
    }
}
