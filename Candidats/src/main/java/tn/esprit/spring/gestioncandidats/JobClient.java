package tn.esprit.spring.gestioncandidats;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "Job")
public interface JobClient {
    @RequestMapping("jobs")
    public List<JobDto> getAllJobs();

    @RequestMapping("jobs/{id}")
    public JobDto getJobById(@PathVariable int id);
}
