package com.example.lokale_assigment.API;

import java.util.List;

import com.example.lokale_assigment.model.Job;
import com.google.gson.annotations.SerializedName;

public class JobResponse {
    @SerializedName("results")
    private List<Job> results;

    public List<Job> getResults() {
        return results;
    }


    public void setResults(List<Job> results) {
        this.results = results;
    }
}
