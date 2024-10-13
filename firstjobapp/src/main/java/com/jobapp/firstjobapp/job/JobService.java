package com.jobapp.firstjobapp.job;

import java.util.*;

public interface JobService {       //interface : for loose coupling & modularity
    List<Job> findAll();
    void createJob(Job job);
    Job getJobById(Long id);
    boolean deleteJobById(Long id);
    boolean deleteAll();
    boolean updateJob(Long id, Job updatedJob);
}
