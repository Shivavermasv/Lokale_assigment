package com.example.lokale_assigment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lokale_assigment.model.Job;

public class Job_item_detail extends AppCompatActivity {

    private TextView titleView, placeView, salaryView, callStartTimeView, callEndTimeView, whatsappNoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_job_item_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize TextViews
        titleView = findViewById(R.id.title);
        placeView = findViewById(R.id.place);
        salaryView = findViewById(R.id.salary);
        callStartTimeView = findViewById(R.id.preferred_call_start_time);
        callEndTimeView = findViewById(R.id.preferred_call_end_time);
        whatsappNoView = findViewById(R.id.whatsapp_no);

        // Retrieve Job object from Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("job")) {
            Job job = (Job) intent.getSerializableExtra ("job");
            if (job != null) {
                populateJobDetails(job);
            }
        }
    }

    private void populateJobDetails(Job job) {
        titleView.setText(job.getTitle() != null ? job.getTitle() : "");

        if (job.getPrimaryDetails() != null) {
            placeView.setText(job.getPrimaryDetails().getPlace() != null ? "Place: " + job.getPrimaryDetails().getPlace() : "");
            salaryView.setText(job.getPrimaryDetails().getSalary() != null ? "Salary: " + job.getPrimaryDetails().getSalary() : "");
        } else {
            placeView.setText("");
            salaryView.setText("");
        }

        if (job.getContactPreference() != null) {
            callStartTimeView.setText(job.getContactPreference().getPreferredCallStartTime() != null ?
                    "Preferred Call Start Time: " + job.getContactPreference().getPreferredCallStartTime() : "");

            callEndTimeView.setText(job.getContactPreference().getPreferredCallEndTime() != null ?
                    "Preferred Call End Time: " + job.getContactPreference().getPreferredCallEndTime() : "");

            whatsappNoView.setText(job.getContactPreference().getWhatsappNo() != null ?
                    "WhatsApp No: " + job.getContactPreference().getWhatsappNo() : "");
        } else {
            callStartTimeView.setText("");
            callEndTimeView.setText("");
            whatsappNoView.setText("");
        }
    }


    public static void jobDetailStartActivity(Job job, Context context) {
        Intent intent = new Intent(context, Job_item_detail.class);
        intent.putExtra("job", job);
        try{
            context.startActivity(intent);
        }
        catch (Exception e){
            e.printStackTrace ();
            Toast.makeText ( context, "JOB NOT FOUND !!", Toast.LENGTH_SHORT ).show ();
        }
    }
}
