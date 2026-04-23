package tn.esprit.spring.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@RepositoryRestResource(path = "jobs")
public interface JobRepository extends JpaRepository<Job, Integer> {

    Optional<Job> findByService(String service);
    List<Job> findAllByServiceContainingIgnoreCase(String service);
}
