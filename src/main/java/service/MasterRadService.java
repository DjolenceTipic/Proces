package service;

import models.MasterRad;
import org.springframework.beans.factory.annotation.Autowired;
import repository.MasterRadRepository;

import java.util.List;

/**
 * Created by Djordje on 4/25/2017.
 */
public class MasterRadService {

    @Autowired
    MasterRadRepository masterRadRepository;

    public MasterRad findOne(Long id) {
        return masterRadRepository.findOne(id);
    }

    public List<MasterRad> findAll() {
        return masterRadRepository.findAll();
    }

    public MasterRad save(MasterRad masterRad) {
        return masterRadRepository.save(masterRad);
    }

    public void remove(Long id) {
        masterRadRepository.delete(id);
    }
}
