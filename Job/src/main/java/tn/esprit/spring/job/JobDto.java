package tn.esprit.spring.job;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobDto {
    public int ID;
    public String Service ;
    public Boolean Etat;
}
