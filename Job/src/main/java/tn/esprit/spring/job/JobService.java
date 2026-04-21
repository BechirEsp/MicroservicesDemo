package tn.esprit.spring.job;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JobService implements IJobService {

    private final JobRepository jobRepository;



    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job getJobById(int id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public Job getJobByService(String service) {
        return jobRepository.findByService(service).orElse(null);
    }

    @Override
    public Job updateEtat(int id, Boolean etat) {
        Job job = jobRepository.findById(id).orElse(null);
        if (job != null) {
            job.setEtat(etat);
            return jobRepository.save(job);
        }
        return null;
    }
}