package tn.esprit.spring.gestioncandidats;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ICandidatService implements CandidateService{

    private List<JobDto> favoriteJobDTOS = new ArrayList<>();

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

    public void receiveJobService(JobDto jobDTO) {
        log.info("Traitement du jobDTO received : {}", jobDTO.getService());
        addJobToFavorites(jobDTO);
        sendNotificationToUser(jobDTO);
    }
    private void addJobToFavorites(JobDto jobDTO) {
        // Ajoutons le JobDTO à la liste en mémoire (cachée)
        favoriteJobDTOS.add(jobDTO);
        log.info("JobDTO ajouté aux favoris : {}", jobDTO.getService());
    }
    private void sendNotificationToUser(JobDto jobDTO) {
        // Simulation de l'envoi d'une notification
        log.info("Notification envoyée au candidat: Nouveau jobDTO - {}",
                jobDTO.getService());
    }

    public List<JobDto> getFavoriteJobsAsync() {
        return favoriteJobDTOS;
    }


}
