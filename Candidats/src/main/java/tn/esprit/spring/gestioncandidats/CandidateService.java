package tn.esprit.spring.gestioncandidats;

import java.util.List;

public interface CandidateService {
    public List<Candidat> getCandidates();
    public List<JobDto> getAllJobs();
    public JobDto getJobById(int id);
    public List<JobDto> getFavoriteJobs(int candidateId);
    public void  saveFavoriteJob(int candidateId, int jobId);
    public void receiveJobService(JobDto jobDTO);
    public List<JobDto> getFavoriteJobsAsync();
}
