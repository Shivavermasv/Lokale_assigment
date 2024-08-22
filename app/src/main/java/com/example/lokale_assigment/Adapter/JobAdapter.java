package com.example.lokale_assigment.Adapter;

import static com.example.lokale_assigment.Job_item_detail.jobDetailStartActivity;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {
    private List<Job> jobList;
    private Context context;
    private JobsDao jobDao;
    private int itemLayoutId;

    public JobAdapter(List<Job> jobList, Context context, int itemLayoutId) {
        this.jobList = jobList != null ? jobList : new ArrayList<>();
        this.context = context;
        this.itemLayoutId = itemLayoutId;
        AppDatabase db = AppDatabase.getDatabase(context);
        jobDao = db.jobDao();
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        Job job = jobList.get(position);
        holder.titleTextView.setText(job.getTitle());
        holder.locationTextView.setText(job.getPrimaryDetails() != null && job.getPrimaryDetails().getPlace() != null ?
                job.getPrimaryDetails().getPlace() : "Place Not Defined");

        holder.itemView.setOnClickListener(view -> jobDetailStartActivity(job, context));

        if (itemLayoutId == R.layout.job_item_job) {
            // Check if the job is bookmarked in the database
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Handler mainHandler = new Handler(Looper.getMainLooper());
            executorService.execute(() -> {
                boolean isBookmarked = jobDao.getJobByTitle(job.getTitle()) == null;
                Log.d ( "api", isBookmarked + " value" );
                Log.d ( "api", job.getTitle() );
                mainHandler.post(() -> {
                    if (!isBookmarked) {
                        // If the job is already bookmarked, disable the bookmark button
                        holder.bookmarkButton.setEnabled ( false );
                        holder.bookmarkButton.setText(R.string.bookmarked);
                    } else {
                        // If the job is not bookmarked, enable the bookmark button
                        holder.bookmarkButton.setEnabled(true);
                        holder.bookmarkButton.setText ( context.getString( R.string.bookmark) );
                        holder.bookmarkButton.setOnClickListener(v -> {
                            executorService.execute(() -> {
                                jobDao.insertJob(job);

                                mainHandler.post(() -> {
                                    holder.bookmarkButton.setEnabled(false);
                                    holder.bookmarkButton.setText(R.string.bookmarked);
                                    Toast.makeText(context, "Bookmarked!!", Toast.LENGTH_SHORT).show();
                                });
                            });
                        });
                    }
                });
            });
        }
    }


    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public void addJobs(List<Job> jobs) {
        int startPosition = jobList.size();
        if (jobs != null && !jobs.isEmpty()) {
            jobList.addAll(jobs);
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
