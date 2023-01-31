package apap.tugas.tugas1_SISDM_2006597456.service;


import apap.tugas.tugas1_SISDM_2006597456.model.Karyawan;
import apap.tugas.tugas1_SISDM_2006597456.model.Presensi;
import apap.tugas.tugas1_SISDM_2006597456.model.Sertifikasi;
import apap.tugas.tugas1_SISDM_2006597456.model.SertifikasiKaryawan;
import apap.tugas.tugas1_SISDM_2006597456.repository.KaryawanDatabase;
import apap.tugas.tugas1_SISDM_2006597456.repository.PresensiDatabase;
import apap.tugas.tugas1_SISDM_2006597456.repository.SertifikasiDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.PresentationDirection;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@Service
@Transactional
public class SertifikasiServiceImpl implements SertifikasiService {

    @Autowired
    SertifikasiDatabase sertifikasiDatabase;

    @Override
    public List<Sertifikasi> getListofSertifikasi() {
        return sertifikasiDatabase.findAll();
    }

    @Override
    public void addSertifikasi(Sertifikasi sertifikasi) {
        sertifikasiDatabase.save(sertifikasi);
    }

    @Override
    public void updateSertifikasi(Sertifikasi sertifikasi) {
        sertifikasiDatabase.save(sertifikasi);
    }

    @Override
    public void deleteSertifikasi(Sertifikasi sertifikasi) {
        sertifikasiDatabase.delete(sertifikasi);
    }

    @Override
    public Sertifikasi getSertifikasiById(int id) {
        return sertifikasiDatabase.findById(id).get();
    }
}
