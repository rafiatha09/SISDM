package apap.tugas.tugas1_SISDM_2006597456.service;

import apap.tugas.tugas1_SISDM_2006597456.model.*;
import apap.tugas.tugas1_SISDM_2006597456.repository.TugasDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class TugasServiceImpl implements TugasService{

    @Autowired
    TugasDatabase tugasDb;

    @Override
    public void addTugas(Tugas tugas){
        tugasDb.save(tugas);
    }

    @Override
    public void updateTugas(Tugas tugas){
        tugasDb.save(tugas);
    }

    @Override
    public void deleteTugas(Tugas tugas){
        tugasDb.delete(tugas);
    }

    @Override
    public List<Tugas> getListofExistTugas(){
        return tugasDb.findAll();
    }

    @Override
    public Tugas getTugasById(int idTugas){
        return tugasDb.findById(idTugas).get();
    }

    @Override
    public Tugas getTugasByName(String name){
        List<Tugas> listTugas = tugasDb.findAll();
        for(Tugas tugas: listTugas){
            if(tugas.getJudulTugas().equals(name)){
                return tugas;
            }
        }
        return null;
    }
}
