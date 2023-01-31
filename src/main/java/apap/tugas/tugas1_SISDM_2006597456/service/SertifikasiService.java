package apap.tugas.tugas1_SISDM_2006597456.service;

import apap.tugas.tugas1_SISDM_2006597456.model.Sertifikasi;

import java.util.List;

public interface SertifikasiService {
    void addSertifikasi(Sertifikasi sertifikasi);
    void updateSertifikasi(Sertifikasi sertifikasi);
    void deleteSertifikasi(Sertifikasi sertifikasi);
    List<Sertifikasi> getListofSertifikasi();
    Sertifikasi getSertifikasiById(int id);
}
