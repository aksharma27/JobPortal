package com.jobapp.firstjobapp.job.impl;

import com.jobapp.firstjobapp.job.Job;
import com.jobapp.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;


@Service
public class JobServiceImpl implements JobService {
    private List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(@RequestBody Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public Job getJobById(Long id) {
        for (Job job : jobs) {
            if (job.getId().equals(id)) return job;
        }
        return null;
    }

    @Override
    public boolean deleteJobById (Long id) {
        for (Job job : jobs) {
            if (job.getId().equals(id)) jobs.remove(job);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAll() {
        if (jobs.size() > 0) {
            for (Job job : jobs) {
                jobs.remove(job);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        for (Job job : jobs) {
            if (job.getId().equals(id)) {
//                jobs.remove(job);
//                jobs.add(updatedJob);
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setLocation(updatedJob.getLocation());
                return true;
            }
        }
        return false;
    }
}
