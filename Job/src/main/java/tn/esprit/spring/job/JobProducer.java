package tn.esprit.spring.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JobProducer {
    private final RabbitTemplate rabbitTemplate;

    // Inject RabbitTemplate (configuré avec Jackson converter)
    public JobProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void sendJob(JobDto job) {
        try {
            rabbitTemplate.convertAndSend(RabbitMQConfig.CANDID_JOB_QUEUE, job);
            log.info("Job envoyé à la queue : ", RabbitMQConfig.CANDID_JOB_QUEUE, job);
        } catch (AmqpException e) {
            log.error("Erreur lors de l'envoi du job à RabbitMQ", e);
            // Remonter l'exception pour que l'appelant sache que l'envoi a échoué
            throw e;
        }
    }
}
