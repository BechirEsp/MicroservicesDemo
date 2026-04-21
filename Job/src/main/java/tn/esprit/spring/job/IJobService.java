package tn.esprit.spring.job;

import java.util.List;

public interface IJobService {
    List<Job> getAllJobs();

    Job getJobById(int id);

    Job getJobByService(String service);

    Job updateEtat(int id, Boolean etat);
}
