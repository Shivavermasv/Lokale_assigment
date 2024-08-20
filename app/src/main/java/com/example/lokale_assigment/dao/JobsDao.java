package com.example.lokale_assigment.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.lokale_assigment.model.Job;

import java.util.List;

@Dao
public interface JobsDao {

    @Insert
    void insertJob(Job job);

    @Delete
    void deleteJob(Job job);

    @Query("SELECT * FROM jobs")
    List<Job> getAllJobs();
}
