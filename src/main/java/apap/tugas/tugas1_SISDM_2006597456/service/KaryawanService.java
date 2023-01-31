package apap.tugas.tugas1_SISDM_2006597456.service;

import apap.tugas.tugas1_SISDM_2006597456.model.Karyawan;
import apap.tugas.tugas1_SISDM_2006597456.model.Sertifikasi;

import java.util.List;

public interface KaryawanService {

    void addKaryawan(Karyawan karyawan);

    List<Karyawan> getListofKaryawan();

    Karyawan getKaryawanbyId(String id);
    List<Sertifikasi> getListofSertifikasi(Karyawan karyawan);

    Karyawan updateKaryawan(Karyawan karyawan);
    void deleteKaryawan(Karyawan karyawan);
}
