package service;

import models.PredlogMastera;
import org.springframework.beans.factory.annotation.Autowired;
import repository.PredlogMasteraRepository;

import java.util.List;

/**
 * Created by Djordje on 4/25/2017.
 */
public class PredlogMasterService {

    @Autowired
    PredlogMasteraRepository predlogMasteraRepository;

    public PredlogMastera findOne(Long id) {
        return predlogMasteraRepository.findOne(id);
    }

    public List<PredlogMastera> findAll() {
        return predlogMasteraRepository.findAll();
    }

    public PredlogMastera save(PredlogMastera predlogMastera) {
        return predlogMasteraRepository.save(predlogMastera);
    }

    public void remove(Long id) {
        predlogMasteraRepository.delete(id);
    }
}
