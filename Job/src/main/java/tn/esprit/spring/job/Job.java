package tn.esprit.spring.job;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int ID;
    public String Service ;
    public Boolean Etat;

    public int getId() { return ID; }
    public void setId(int id) { this.ID = id; }

    public String getService() { return Service; }
    public void setService(String service) { this.Service = service; }

    public Boolean getEtat() { return Etat; }
    public void setEtat(Boolean etat) { this.Etat = etat; }


}
