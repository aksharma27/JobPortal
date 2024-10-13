package com.jobapp.firstjobapp.job;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    private List<Job> jobs = new ArrayList<>(); //since we don't have a db, we'll use this list for jobs

    @GetMapping
    public ResponseEntity<List<Job>> findAll() {
        List<Job> job = jobService.findAll();
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job Added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        if (job != null) return new ResponseEntity<>(job, HttpStatus.OK);
        Job notFound = new Job((long) 0, "No job found", "", "", "", "");
        return new ResponseEntity<>(notFound, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById (@PathVariable Long id) {
        boolean deleted = jobService.deleteJobById(id);
        if (deleted)
            return new ResponseEntity<>("Job with id : " + id + " deleted Successfully", HttpStatus.OK);
        return new ResponseEntity<>("Job of id : " + id + " doesn't exist", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/jobs")
    public ResponseEntity<String> deleteAllJobs () {
        boolean empty = jobService.deleteAll();
        if (!empty) //returns true
            return new ResponseEntity<>("All jobs deleted successfully", HttpStatus.OK);
        //else : job list is already empty :
        return new ResponseEntity<>("Already Empty", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJobById (@PathVariable Long id, @RequestBody Job updatedJob) {
        boolean updated = jobService.updateJob(id, updatedJob);
        if (updated)
            return new ResponseEntity<>("Job " + id + " updated successfully", HttpStatus.OK);
        return new ResponseEntity<>("Error updating job", HttpStatus.NOT_FOUND);
    }
}
