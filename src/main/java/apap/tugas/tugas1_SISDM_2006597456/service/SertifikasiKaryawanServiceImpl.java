package apap.tugas.tugas1_SISDM_2006597456.service;

import apap.tugas.tugas1_SISDM_2006597456.model.Karyawan;
import apap.tugas.tugas1_SISDM_2006597456.model.SertifikasiKaryawan;
import apap.tugas.tugas1_SISDM_2006597456.repository.SertifikasiKaryawanDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
public class SertifikasiKaryawanServiceImpl implements SertifikasiKaryawanService {

    @Autowired
    SertifikasiKaryawanDatabase sertifikasiKaryawanDatabase;

    @Override
    public SertifikasiKaryawan getSertifikasiKaryawan(int id){
        return sertifikasiKaryawanDatabase.findById(id).get();
    }

    @Override
    public String generateSertifikasiId(LocalDate tahunLahirkaryawan, LocalDate tanggalPengambilanSertifikat, String namaSertifikat, String namaKaryawan, int idKaryawan) {
        String noSertifikasi = "SER";
        int tahun_lahir = Integer.parseInt(tahunLahirkaryawan.toString().substring(0,4));
        int ddm = Integer.parseInt(tanggalPengambilanSertifikat.toString().substring(5,7) + tanggalPengambilanSertifikat.toString().substring(8));

        // menjumlahkan tahun lahir karyawan + ddmm pengambilan sertifikat
        noSertifikasi = noSertifikasi + Integer.toString(tahun_lahir + ddm);

        // dari abjad pertama nama sertifikat
        int nomors = (Character.getNumericValue(namaSertifikat.charAt(0)) - 9);
        if(nomors < 10){
            noSertifikasi = noSertifikasi + '0' + Integer.toString(nomors) + '0';
        }
        else{
            noSertifikasi = noSertifikasi + Integer.toString(nomors) + '0';
        }

        // dari abjad pertama nama karyawan
        int kars = Character.getNumericValue(namaKaryawan.charAt(0)) - 9;
        if(kars < 10){
            noSertifikasi = noSertifikasi + '0' + Integer.toString(kars) + '0';
        }
        else{
            noSertifikasi = noSertifikasi + Integer.toString(kars) + '0';
        }

        // dari id karyawan
        if( idKaryawan < 9){
            noSertifikasi = noSertifikasi + '0' + idKaryawan;
        }
        else{
            noSertifikasi = noSertifikasi + idKaryawan;
        }

        return noSertifikasi;
    }

    @Override
    public void deleteSertifikasiKaryawan(SertifikasiKaryawan sertifikasiKaryawan){
        sertifikasiKaryawanDatabase.delete(sertifikasiKaryawan);
    }

}
