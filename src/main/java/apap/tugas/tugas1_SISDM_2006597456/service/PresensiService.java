package apap.tugas.tugas1_SISDM_2006597456.service;

import apap.tugas.tugas1_SISDM_2006597456.model.Presensi;

import java.util.List;

public interface PresensiService {
     void addPresensi(Presensi presensi);
    List<Presensi> getListofPresensi();
    Presensi getPresensibyId(Integer id);
    void updatePresensi(Presensi presensi);

}
