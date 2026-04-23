package tn.esprit.spring.job;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Value("${welcome.message}")
    private String message;

    @GetMapping("/hello")
    public String hello() {
        return message;
    }
}
