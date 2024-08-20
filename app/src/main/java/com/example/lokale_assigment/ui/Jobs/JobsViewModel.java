package com.example.lokale_assigment.ui.Jobs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lokale_assigment.model.Job;

import java.util.ArrayList;
import java.util.List;

public class JobsViewModel extends ViewModel {
    private final MutableLiveData<List<Job>> jobListLiveData = new MutableLiveData<>();
    private List<Job> jobList = new ArrayList<> ();

    public LiveData<List<Job>> getJobList() {
        return jobListLiveData;
    }

    public void setJobList(List<Job> jobs) {
        jobList.addAll(jobs);
        jobListLiveData.setValue(jobList);
    }

    public void clearJobs() {
        jobList.clear();
        jobListLiveData.setValue(jobList);
    }
}
