package com.example.lokale_assigment.Adapter;

import static com.example.lokale_assigment.Job_item_detail.jobDetailStartActivity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lokale_assigment.Databse.AppDatabase;
import com.example.lokale_assigment.R;
import com.example.lokale_assigment.dao.JobsDao;
import com.example.lokale_assigment.model.Job;

import java.util.ArrayList;
import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {
    private List<Job> jobList;
    private Context context;
    private JobsDao jobDao;  // Reference to DAO for database operations
    private int item;
    public JobAdapter(List<Job> jobList, Context context, int item) {
        if (jobList == null) {
            this.jobList = new ArrayList<>();
        } else {
            this.jobList = jobList;
        }
        this.context = context;
        this.item = item;
        AppDatabase db = AppDatabase.getDatabase(context);
        jobDao = db.jobDao();
    }
    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(item, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        Job job = jobList.get(position);
        holder.titleTextView.setText(job.getTitle());
        //holder.locationTextView.setText(job.getPrimaryDetails ().getPlace ());

        holder.itemView.setOnClickListener ( view -> {
            jobDetailStartActivity(jobList.get ( position ), context);
        } );
        // Set click listener for the bookmark button
        if(item == R.layout.job_item_job){

            holder.bookmarkButton.setOnClickListener(v -> {
                // Add the job to the database
                try{
                    jobDao.insertJob(job);
                    holder.bookmarkButton.setEnabled ( false );
                    holder.bookmarkButton.setText( R.string.bookmarked);
                    Toast.makeText(context, "Job Bookmarked!", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    e.printStackTrace();
                    Log.d("api", e.toString ());
                    Toast.makeText(context, "Job Already Bookmarked!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public void addJobs(List<Job> jobs) {
        int startPosition = jobList.size();
        if(jobs != null){
            jobList.addAll ( jobs );
            notifyItemRangeInserted(startPosition, jobs.size());
        }
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView locationTextView;
        Button bookmarkButton;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.job_title);
            locationTextView = itemView.findViewById(R.id.job_location);
            bookmarkButton = itemView.findViewById(R.id.bookmark_button);
        }
    }
}


