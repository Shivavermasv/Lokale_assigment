package com.example.lokale_assigment.ui.Jobs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lokale_assigment.API.JobResponse;
import com.example.lokale_assigment.API.Service.JobApiService;
import com.example.lokale_assigment.API.instance.RetrofitClient;
import com.example.lokale_assigment.Adapter.EndlessRecyclerViewScrollListener;
import com.example.lokale_assigment.Adapter.JobAdapter;
import com.example.lokale_assigment.R;
import com.example.lokale_assigment.databinding.JobsFragmentBinding;
import com.example.lokale_assigment.model.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class JobsFragment extends Fragment {

    private JobsFragmentBinding binding;
    private RecyclerView jobsRecyclerView;
    private JobAdapter jobAdapter;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = 1;
    private final int pageSize = 100;  // Assuming the page size
    private JobsViewModel jobsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        jobsViewModel = new ViewModelProvider(this).get(JobsViewModel.class);

        // Hide the action bar
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        }

        binding = JobsFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Initialize RecyclerView and Adapter
        jobsRecyclerView = view.findViewById(R.id.jobs_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        jobsRecyclerView.setLayoutManager(layoutManager);

        // Initialize adapter and set it
        jobAdapter = new JobAdapter(new ArrayList<>(), getContext(), R.layout.job_item_job);
        jobsRecyclerView.setAdapter(jobAdapter);

        // Observe job list data from ViewModel
        jobsViewModel.getJobList().observe(getViewLifecycleOwner(), jobs -> {
            if (jobs != null) {
                jobAdapter.addJobs(jobs);
            }
        });

        // Fetch initial data
        fetchJobsFromApi(currentPage);

        // Set scroll listener for infinite scrolling
        jobsRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void loadMoreItems() {
                isLoading = true;
                currentPage++;
                fetchJobsFromApi(currentPage);
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public int getPageSize() {
                return pageSize;
            }
        });

        return view;
    }

    private void fetchJobsFromApi(int currentPage) {
        // Initialize Retrofit with your base URL
        Retrofit retrofit = RetrofitClient.getClient("https://testapi.getlokalapp.com/");
        JobApiService jobApiService = retrofit.create(JobApiService.class);

        // Call the API to get jobs
        Call<JobResponse> call = jobApiService.getJobs(currentPage);
        call.enqueue(new Callback<JobResponse>() {
            @Override
            public void onResponse(@NonNull Call<JobResponse> call, @NonNull Response<JobResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Add the new jobs to the ViewModel
                    List<Job> newJobs = response.body().getResults();
                    jobsViewModel.setJobList(newJobs);

                    isLastPage = false;
                } else {
                    Toast.makeText(getContext(), "Failed to load more data!", Toast.LENGTH_SHORT).show();
                }
                isLoading = false;
            }

            @Override
            public void onFailure(@NonNull Call<JobResponse> call, @NonNull Throwable t) {
                Log.d("api", Objects.requireNonNull(t.getMessage()));
                isLoading = true;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
