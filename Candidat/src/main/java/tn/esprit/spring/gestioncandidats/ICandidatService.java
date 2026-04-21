package tn.esprit.spring.gestioncandidats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ICandidatService implements CandidateService{
@Autowired
private CandidatRepository candidatRepository;

@Autowired
private JobClient jobclient;
    @Override
    public List<Candidat> getCandidates() {
        return candidatRepository.findAll();
    }

    public List<JobDto> getAllJobs() {
        return jobclient.getAllJobs();
    }
    public JobDto getJobById(int id) {
        return jobclient.getJobById(id);
    }

    public List<JobDto> getFavoriteJobs(int candidateId) {
        Candidat candidate = candidatRepository.findById(candidateId).orElse(null);
        return candidate.getFavoriteJobs().stream()
                .map(jobclient::getJobById)
                .collect(Collectors.toList());
    }

    public void saveFavoriteJob(int candidateId, int jobId) {
        Candidat candidate = candidatRepository.findById(candidateId).get();
        candidate.getFavoriteJobs().add(jobId);
        candidatRepository.save(candidate);
        System.out.println("Job with ID " + jobId + " saved as favorite for candidate with ID " + candidateId);

    }
}
