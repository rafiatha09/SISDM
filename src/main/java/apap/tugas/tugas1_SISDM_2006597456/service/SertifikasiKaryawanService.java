package apap.tugas.tugas1_SISDM_2006597456.service;


import apap.tugas.tugas1_SISDM_2006597456.model.SertifikasiKaryawan;

import java.time.LocalDate;

public interface SertifikasiKaryawanService {
    String generateSertifikasiId(LocalDate tahunLahirkaryawan, LocalDate tanggalPengambilanSertifikat, String namaSertifikat, String namaKaryawan, int idKaryawan);
    void deleteSertifikasiKaryawan(SertifikasiKaryawan sertifikasiKaryawan);

    SertifikasiKaryawan getSertifikasiKaryawan(int id);
}
