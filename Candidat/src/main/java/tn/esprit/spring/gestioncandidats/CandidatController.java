package tn.esprit.spring.gestioncandidats;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidats")
public class CandidatController {
    private final CandidateService candidateService;

    public CandidatController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello from MicroService Candidat ";
    }

    @GetMapping
    public List<Candidat> getCandidates() {
        return candidateService.getCandidates();
    }


    @GetMapping("/jobs")
    public List<JobDto> getJobs() {
        return candidateService.getAllJobs();
    }

    @GetMapping("/jobs/{id}")
    public JobDto getJobById(@PathVariable int id) {
        return candidateService.getJobById(id);
    }

    @GetMapping("/{id}/favorite-jobs")
    public List<JobDto> getFavoriteJobs(@PathVariable int id) {
        return candidateService.getFavoriteJobs(id);
    }

    @PostMapping("/{id}/favorite-jobs/{jobId}")
    public ResponseEntity<String> saveFavoriteJob(@PathVariable int id, @PathVariable int jobId) {
        JobDto job = candidateService.getJobById(jobId);
        if (job != null) {
            candidateService.saveFavoriteJob(id, jobId);
            return ResponseEntity.status(HttpStatus.OK).body("Job saved as favorite successfully.");

        } else {
            // Gérer le cas où le job n'existe pas
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Job not found with ID: " + jobId);
        }
    }

}
