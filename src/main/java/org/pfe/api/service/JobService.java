package org.pfe.api.service;

import org.pfe.api.bean.Job;
import org.pfe.api.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> getAll() {
        return jobRepository.getAllJobs();
    }

    public void addAll(List<Job> jobs) {
        jobs.forEach(jobRepository::addJob);
    }

    public List<Job> searchByTitleOrDescription(String query) {
        return jobRepository.searchByTitleOrDescription(query);
    }
}
