package apap.tugas.tugas1_SISDM_2006597456.service;
import apap.tugas.tugas1_SISDM_2006597456.model.Karyawan;
import apap.tugas.tugas1_SISDM_2006597456.model.Presensi;
import apap.tugas.tugas1_SISDM_2006597456.model.Sertifikasi;
import apap.tugas.tugas1_SISDM_2006597456.model.SertifikasiKaryawan;
import apap.tugas.tugas1_SISDM_2006597456.repository.KaryawanDatabase;
import apap.tugas.tugas1_SISDM_2006597456.repository.PresensiDatabase;
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
public class PresensiServiceImpl implements PresensiService{

    @Autowired
    PresensiDatabase presensiDatabase;

    @Override
    public void updatePresensi(Presensi presensi){
        presensiDatabase.save(presensi);
    }
    @Override
    public void addPresensi(Presensi presensi){
        presensiDatabase.save(presensi);
    }
    @Override
    public List<Presensi> getListofPresensi(){
        return presensiDatabase.findAll();
    }
    @Override
    public Presensi getPresensibyId(Integer id){
        Optional<Presensi> presensi = presensiDatabase.findById(id);
        if(presensi.isPresent()){
            return presensi.get();
        }
        else return null;
    }

}
