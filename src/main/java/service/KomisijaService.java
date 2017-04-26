package service;

import models.Komisija;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.KomisijaRepository;

import java.util.List;

/**
 * Created by Djordje on 4/24/2017.
 */
@Service
public class KomisijaService {
    @Autowired
    KomisijaRepository komisijaRepository;

    public Komisija findOne(Long id) {
        return komisijaRepository.findOne(id);
    }

    public List<Komisija> findAll() {
        return komisijaRepository.findAll();
    }

    public Komisija save(Komisija course) {
        return komisijaRepository.save(course);
    }

    public void remove(Long id) {
        komisijaRepository.delete(id);
    }
}
