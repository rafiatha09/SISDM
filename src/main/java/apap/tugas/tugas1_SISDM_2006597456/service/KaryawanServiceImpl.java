package apap.tugas.tugas1_SISDM_2006597456.service;

import apap.tugas.tugas1_SISDM_2006597456.model.Karyawan;
import apap.tugas.tugas1_SISDM_2006597456.model.Presensi;
import apap.tugas.tugas1_SISDM_2006597456.model.Sertifikasi;
import apap.tugas.tugas1_SISDM_2006597456.model.SertifikasiKaryawan;
import apap.tugas.tugas1_SISDM_2006597456.repository.KaryawanDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@Service
@Transactional
public class KaryawanServiceImpl implements KaryawanService{

    @Autowired
    KaryawanDatabase karyawandb;
    // menambahkan karyawan (3)
    @Override
    public void addKaryawan(Karyawan karyawan){
        karyawandb.save(karyawan);
    }
    @Override
    public Karyawan updateKaryawan(Karyawan karyawan){
        karyawandb.save(karyawan);
        return karyawan;
    }

    @Override
    public void deleteKaryawan(Karyawan karyawan){
        karyawandb.delete(karyawan);
    }
    @Override
    public List<Karyawan> getListofKaryawan(){
        return karyawandb.findAll();
    }
    @Override
    public Karyawan getKaryawanbyId(String id){
        Optional<Karyawan> karyawan = karyawandb.findById((int) Long.parseLong(id));
        if(karyawan.isPresent()){
            return karyawan.get();
        }
        else return null;
    }
    @Override
    public List<Sertifikasi> getListofSertifikasi(Karyawan karyawan){
        List<SertifikasiKaryawan> sertifikasikaryawan = karyawan.getListSertifikasiKaryawan();
        // mengambil semua sertfikasi yang dimiliki karyawan di model SertfikasiKaryawan
        List<Sertifikasi> listSertfikasi = new ArrayList<>();
        for(SertifikasiKaryawan sertifikasi: sertifikasikaryawan){
            listSertfikasi.add(sertifikasi.getSertifikasi());
        }
        return listSertfikasi;

    }

}
