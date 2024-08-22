package com.example.lokale_assigment.ui.BookMark;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lokale_assigment.Adapter.JobAdapter;
import com.example.lokale_assigment.Databse.AppDatabase;
import com.example.lokale_assigment.R;
import com.example.lokale_assigment.databinding.BookmarkFragmentBinding;
import com.example.lokale_assigment.model.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookmarkFragment extends Fragment {
    private RecyclerView bookmarksRecyclerView;
    private JobAdapter jobAdapter;
    private List<Job> bookmarkedJobs;

    private BookmarkFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        }

        binding = BookmarkFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        bookmarksRecyclerView = view.findViewById(R.id.bookmarks_recycler_view);
        bookmarksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        bookmarkedJobs = new ArrayList<>();
        jobAdapter = new JobAdapter(bookmarkedJobs, getContext(), R.layout.job_item_bookmark);
        bookmarksRecyclerView.setAdapter(jobAdapter);

        AppDatabase db = AppDatabase.getDatabase(getContext());
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler mainHandler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Job> jobs = db.jobDao().getAllJobs();
                Log.d("api", "Fetched jobs: " + jobs.size());

                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        bookmarkedJobs.clear();
                        bookmarkedJobs.addAll(jobs);
                        jobAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
