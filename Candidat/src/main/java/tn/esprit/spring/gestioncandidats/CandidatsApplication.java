package tn.esprit.spring.gestioncandidats;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CandidatsApplication {

    private final CandidatRepository repository;

    public CandidatsApplication(CandidatRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(CandidatsApplication.class, args);
    }

    @Bean
    ApplicationRunner init() {
        return (args) -> {
            if (repository.count() == 0) {
                repository.save(new Candidat("Mariem", "Ch", "ma@esprit.tn"));
                repository.save(new Candidat("Sarra", "ab", "sa@esprit.tn"));
                repository.save(new Candidat("Mohamed", "ba", "mo@esprit.tn"));
                repository.save(new Candidat("Maroua", "dh", "maroua@esprit.tn"));
            }
            repository.findAll().forEach(System.out::println);
        };
    }
}
