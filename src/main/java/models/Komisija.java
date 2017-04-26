package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Djordje on 4/24/2017.
 */
@Entity
public class Komisija {

    @Id
    @GeneratedValue
    private Long id;

    String ime;

    String prezime;

    String status;

    @ManyToOne
    MasterRad masterRad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MasterRad getMasterRad() {
        return masterRad;
    }

    public void setMasterRad(MasterRad masterRad) {
        this.masterRad = masterRad;
    }
}
