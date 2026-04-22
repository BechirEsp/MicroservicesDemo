package tn.esprit.spring.job;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class JobService implements IJobService {

    private final JobRepository jobRepository;
    private final JobProducer jobProducer;


    public JobService(JobRepository jobRepository,JobProducer jobProducer) {
        this.jobRepository = jobRepository;
        this.jobProducer = jobProducer;
    }


    @Transactional
    public Job saveAndSendJob(Job jobEntity) {
        Job savedJob = jobRepository.save(jobEntity);
        log.info("Job sauvegardé en base : {}", savedJob.getService());
        // Construire un DTO à envoyer
        JobDto jobDTO = new JobDto(savedJob.getId(), savedJob.getService(), savedJob.getEtat());
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
}