package tn.esprit.spring.gestioncandidats;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidats")
@RefreshScope
public class Controller {

    @Value("${welcome.message}")
    private String message;

    @GetMapping("/hello")
    public String hello() {
        return message;
    }
}
