package com.example.lokale_assigment.API.Service;

import com.example.lokale_assigment.API.JobResponse;
import com.example.lokale_assigment.model.Job;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface JobApiService {

    @GET("common/jobs")
    Call<JobResponse> getJobs(@Query("page") int page);
}
