package tn.esprit.spring.job;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Document(indexName = "jobs", createIndex = true)
@Data
@AllArgsConstructor
public class JobDocument {

    @Id
    private String id;

    private String service;

    private Boolean etat;

}
