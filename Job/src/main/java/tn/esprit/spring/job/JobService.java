package tn.esprit.spring.job;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class JobService implements IJobService {

    private final JobRepository jobRepository;
    private final JobProducer jobProducer;
    private final JobSearchRepository jobSearchRepository;

    public JobService(JobRepository jobRepository, JobProducer jobProducer, JobSearchRepository jobSearchRepository) {
        this.jobRepository = jobRepository;
        this.jobProducer = jobProducer;
        this.jobSearchRepository = jobSearchRepository;
    }


    @Transactional
    public Job saveAndSendJob(Job jobEntity) {
        Job savedJob = jobRepository.save(jobEntity);
        JobDocument jobdoc = new JobDocument("job-"+savedJob.getID(),savedJob.Service,savedJob.Etat);
        jobSearchRepository.save(jobdoc);
        log.info("Job sauvegardé en base : {}", savedJob.getService());
        // Construire un DTO à envoyer
        JobDto jobDTO = new JobDto(savedJob.getID(), savedJob.getService(), savedJob.getEtat());
        // Envoi asynchrone via RabbitMQ
        jobProducer.sendJob(jobDTO);
        return savedJob;
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

    public List<JobDocument>  findByServiceIsContainingIgnoreCase(String service) {
        return jobSearchRepository.findByServiceIsContainingIgnoreCase(service);
    }

    public List<Job> findByServiceSQL(String service) {
        return jobRepository.findAllByServiceContainingIgnoreCase(service);
    }
}