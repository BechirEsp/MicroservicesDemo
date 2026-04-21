package tn.esprit.spring.gestioncandidats;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JobDto {
    private  int ID;
    private String Service;
    private boolean Etat;
}
