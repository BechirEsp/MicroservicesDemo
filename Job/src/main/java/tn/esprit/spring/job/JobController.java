package tn.esprit.spring.job;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("{id}")
    public Job getJobById(@PathVariable int id) {
        return jobService.getJobById(id);
    }
    // Modifier état
    @PutMapping("/{id}/etat")
    public Job updateEtat(@PathVariable int id,
                          @RequestParam Boolean etat) {
        return jobService.updateEtat(id, etat);
    }
}