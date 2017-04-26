package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Djordje on 4/24/2017.
 */
@Entity
public class PredlogMastera {
    @Id
    @GeneratedValue
    private Long id;

    String ime;

    String prezime;

    String tema;

    String mentor;

    Date datumPredaje;

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

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    public Date getDatumPredaje() {
        return datumPredaje;
    }

    public void setDatumPredaje(Date datumPredaje) {
        this.datumPredaje = datumPredaje;
    }
}
