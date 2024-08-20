package com.example.lokale_assigment.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "jobs", indices = {@Index(value = {"title"}, unique = true)})
public class Job implements Parcelable {
    public Job(){

    }
    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("primary_details")
    private PrimaryDetails primaryDetails;

    @SerializedName("contact_preference")
    private ContactPreference contactPreference;

    protected Job(Parcel in) {
        id = in.readInt();
        title = in.readString();
        primaryDetails = in.readParcelable(PrimaryDetails.class.getClassLoader());
        contactPreference = in.readParcelable(ContactPreference.class.getClassLoader());
    }

    public static final Creator<Job> CREATOR = new Creator<Job>() {
        @Override
        public Job createFromParcel(Parcel in) {
            return new Job(in);
        }

        @Override
        public Job[] newArray(int size) {
            return new Job[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeParcelable(primaryDetails, flags);
        dest.writeParcelable(contactPreference, flags);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public static class PrimaryDetails implements Parcelable {
        @SerializedName("Place")
        private String place;

        @SerializedName("Salary")
        private String salary;

        protected PrimaryDetails(Parcel in) {
            place = in.readString();
            salary = in.readString();
        }

        public static final Creator<PrimaryDetails> CREATOR = new Creator<PrimaryDetails>() {
            @Override
            public PrimaryDetails createFromParcel(Parcel in) {
                return new PrimaryDetails(in);
            }

            @Override
            public PrimaryDetails[] newArray(int size) {
                return new PrimaryDetails[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(place);
            dest.writeString(salary);
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

    public static class ContactPreference implements Parcelable {
        @SerializedName("preferred_call_start_time")
        private String preferredCallStartTime;

        @SerializedName("preferred_call_end_time")
        private String preferredCallEndTime;

        @SerializedName("whatsapp_no")
        private String whatsappNo;

        protected ContactPreference(Parcel in) {
            preferredCallStartTime = in.readString();
            preferredCallEndTime = in.readString();
            whatsappNo = in.readString();
        }

        public static final Creator<ContactPreference> CREATOR = new Creator<ContactPreference>() {
            @Override
            public ContactPreference createFromParcel(Parcel in) {
                return new ContactPreference(in);
            }

            @Override
            public ContactPreference[] newArray(int size) {
                return new ContactPreference[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(preferredCallStartTime);
            dest.writeString(preferredCallEndTime);
            dest.writeString(whatsappNo);
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
