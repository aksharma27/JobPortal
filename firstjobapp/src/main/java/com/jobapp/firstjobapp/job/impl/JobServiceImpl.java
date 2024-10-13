package com.jobapp.firstjobapp.job.impl;

import com.jobapp.firstjobapp.job.Job;
import com.jobapp.firstjobapp.job.JobRepository;
import com.jobapp.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class JobServiceImpl implements JobService {
//    private List<Job> jobs = new ArrayList<>(); --> use jobrepository instead :
    JobRepository jobRepository;

    public JobServiceImpl (JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
//        return jobs; -> instead of list, use jobRepositry.findAll();
        return jobRepository.findAll();
    }

    @Override
    public void createJob(@RequestBody Job job) {
//        job.setId(nextId++);
//        jobs.add(job);
        jobRepository.save(job);        //save, findAll, etc are methods from jpa.presistence package
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById (Long id) {
//        for (Job job : jobs) {
//            if (job.getId().equals(id)) jobs.remove(job);
//            return true;
//        }
//        return false;
        try {
            jobRepository.deleteById(id);       //delete method provided by jpa
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteAll() {
//        if (jobs.size() > 0) {
//            for (Job job : jobs) {
//                jobs.remove(job);
//            }
//            return true;
//        }
//        return false;
        jobRepository.deleteAll();
        return true;
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
//        for (Job job : jobs) {
//            if (job.getId().equals(id)) {
////                jobs.remove(job);
////                jobs.add(updatedJob);
//                job.setTitle(updatedJob.getTitle());
//                job.setDescription(updatedJob.getDescription());
//                job.setMinSalary(updatedJob.getMinSalary());
//                job.setMaxSalary(updatedJob.getMaxSalary());
//                job.setLocation(updatedJob.getLocation());
//                return true;
//            }
//        }
//        return false;
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
