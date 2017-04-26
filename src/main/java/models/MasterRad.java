package models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Djordje on 4/24/2017.
 */
@Entity
public class MasterRad {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "masterRad", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Set<Komisija> komisijaSet = new HashSet<Komisija>();

    Date datumOdbrane;

    double ocena;

    @OneToOne
    PredlogMastera predlogMastera;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Komisija> getKomisijaSet() {
        return komisijaSet;
    }

    public void setKomisijaSet(Set<Komisija> komisijaSet) {
        this.komisijaSet = komisijaSet;
    }

    public Date getDatumOdbrane() {
        return datumOdbrane;
    }

    public void setDatumOdbrane(Date datumOdbrane) {
        this.datumOdbrane = datumOdbrane;
    }

    public double getOcena() {
        return ocena;
    }

    public void setOcena(double ocena) {
        this.ocena = ocena;
    }

    public PredlogMastera getPredlogMastera() {
        return predlogMastera;
    }

    public void setPredlogMastera(PredlogMastera predlogMastera) {
        this.predlogMastera = predlogMastera;
    }
}
