package tn.esprit.spring.job;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface JobSearchRepository extends ElasticsearchRepository<JobDocument, String> {
    List<JobDocument> findByServiceIsContainingIgnoreCase(String service);
}
