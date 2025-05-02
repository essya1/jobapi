package org.pfe.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.pfe.api.bean.Job;
import org.pfe.api.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;


    @Operation(summary = "Get all jobs", description = "Retrieves a list of all jobs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchJobs(@RequestParam String query) {
        List<Job> results = jobService.searchByTitleOrDescription(query);
        return ResponseEntity.ok(results);
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAll();
    }

    @PostMapping
    public ResponseEntity<String> addJobs(@RequestBody List<Job> jobs) {
        jobService.addAll(jobs);
        return ResponseEntity.ok("Jobs successfully added!");
    }
}
