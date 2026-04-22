package tn.esprit.spring.gestioncandidats;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class JobConsumer {
    private final CandidateService candidatService;

    public JobConsumer(CandidateService candidatService) {
        this.candidatService = candidatService;
    }
    // Ecoute la file CANDID_JOB_QUEUE
    // Spring convertit automatiquement le JSON en JobDTO grâce au MessageConverter
    @RabbitListener(queues = RabbitMQConfig.CANDID_JOB_QUEUE, containerFactory =
            "rabbitListenerContainerFactory")
    public void receiveJob(JobDto jobDTO) {
        log.info("JobDTO reçu depuis RabbitMQ : {}", jobDTO);
        candidatService.receiveJobService(jobDTO);
    }
}
