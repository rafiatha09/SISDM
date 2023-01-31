package apap.tugas.tugas1_SISDM_2006597456.service;

import apap.tugas.tugas1_SISDM_2006597456.model.Sertifikasi;
import apap.tugas.tugas1_SISDM_2006597456.model.Tugas;

import java.util.List;

public interface TugasService {
    void addTugas(Tugas tugas);
    void updateTugas(Tugas tugas);
    void deleteTugas(Tugas tugas);
    List<Tugas> getListofExistTugas();

    Tugas getTugasByName(String name);
    Tugas getTugasById(int idTugas);
}
