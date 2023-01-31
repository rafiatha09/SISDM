package apap.tugas.tugas1_SISDM_2006597456.controller;

import apap.tugas.tugas1_SISDM_2006597456.model.Sertifikasi;
import apap.tugas.tugas1_SISDM_2006597456.model.Karyawan;
import apap.tugas.tugas1_SISDM_2006597456.model.SertifikasiKaryawan;
//import apap.tugas.tugas1_SISDM_2006597456.model.SertifikasiKaryawanKey;
import apap.tugas.tugas1_SISDM_2006597456.service.KaryawanService;
import apap.tugas.tugas1_SISDM_2006597456.service.SertifikasiKaryawanService;
import apap.tugas.tugas1_SISDM_2006597456.service.SertifikasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.SysexMessage;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class KaryawanController {
    List<SertifikasiKaryawan> temporarysertifikasi = new ArrayList<>();

    @Qualifier("karyawanServiceImpl")
    @Autowired
    private KaryawanService karyawanService;

    @Qualifier("sertifikasiServiceImpl")
    @Autowired
    private SertifikasiService sertifikasiService;

    @Qualifier("sertifikasiKaryawanServiceImpl")
    @Autowired
    private SertifikasiKaryawanService sertifikasiKaryawanService;


    @GetMapping("karyawan/")
    public String getListKaryawanPage(Model model) {
        List<Karyawan> listkaryawan = karyawanService.getListofKaryawan();
        model.addAttribute("listkaryawan", listkaryawan);
        return "all-karyawan";
    }

    // Pertambahan Karyawan
    @GetMapping("/karyawan/tambah")
    public String addKaryawanPage(Model model) {
        Karyawan karyawan = new Karyawan();
        temporarysertifikasi.add(new SertifikasiKaryawan());
        karyawan.setListSertifikasiKaryawan(temporarysertifikasi);
        List<Sertifikasi> listofsertifikasiExist = sertifikasiService.getListofSertifikasi();
        model.addAttribute("listofsertifikasiexist", listofsertifikasiExist);
        model.addAttribute("karyawan", karyawan);
        return "form-add-karyawan";
    }

    @PostMapping(value = "/karyawan/tambah", params = {"submitForm"})
    public String addKaryawanSubmit(@ModelAttribute Karyawan karyawan, Model model) { // karena pasti ada sertifikasi karyawan
        if (karyawan.getListSertifikasiKaryawan().size() == 1 && karyawan.getListSertifikasiKaryawan().get(0).getTanggalPengambilan() == null) {
            karyawan.setListSertifikasiKaryawan(new ArrayList<>());
            karyawanService.addKaryawan(karyawan);
            model.addAttribute("namaKaryawan", karyawan.getNamaDepan() + " " + karyawan.getNamaBelakang());
            return "add-karyawan";
        }
        List<SertifikasiKaryawan> temporary = new ArrayList<>();
        for(SertifikasiKaryawan var : karyawan.getListSertifikasiKaryawan()){
            System.out.println(var.getSertifikasi().getNama());
            var.setKaryawan(karyawan);
//            var.setNoSertifikasi(sertifikasiKaryawanService.generateSertifikasiId(karyawan.getTanggalLahir(),var.getTanggalPengambilan(), var.getSertifikasi().getNama(),karyawan.getNamaDepan(), karyawan.getIdKaryawan()));
            var.setNoSertifikasi("HFJDSFKDFJSF");
            temporary = var.getSertifikasi().getListSertifikasiKaryawan();
            if(temporary == null || temporary.size() == 0){
                temporary.add(var);
                var.getSertifikasi().setListSertifikasiKaryawan(temporary);
            }
            else{
                var.getSertifikasi().getListSertifikasiKaryawan().add(var);
            }
            temporary = new ArrayList<>();

        }
        System.out.println("dfdfd");
        karyawanService.addKaryawan(karyawan);
//        System.out.println(karyawan.getListSertifikasiKaryawan().get(0).getSertifikasi().getListSertifikasiKaryawan().size());
        System.out.println("disini");
        temporarysertifikasi = new ArrayList<>();
        model.addAttribute("namaKaryawan", karyawan.getNamaDepan() + " " + karyawan.getNamaBelakang());
        return "add-karyawan";
    }

    @PostMapping(value = "/karyawan/tambah", params = {"addRowSertifikasi"})
    private String addRowSertifikasi(@ModelAttribute Karyawan karyawan, Model model) {
        SertifikasiKaryawan sertifikasi = new SertifikasiKaryawan();
//        sertifikasi.setKaryawan(karyawan); // set karyawan
//        sertifikasi.setKeyid(new SertifikasiKaryawanKey());
        temporarysertifikasi = karyawan.getListSertifikasiKaryawan();
        temporarysertifikasi.add(sertifikasi);

        // set object karyawan
        for (SertifikasiKaryawan var : temporarysertifikasi) {
            var.setKaryawan(karyawan);
        }
        // set sertifikasi karyawan di karyawan
        karyawan.setListSertifikasiKaryawan(temporarysertifikasi);
        temporarysertifikasi = new ArrayList<>();
        List<Sertifikasi> listofsertifikasiExist = sertifikasiService.getListofSertifikasi();
        model.addAttribute("listofsertifikasiexist", listofsertifikasiExist);
        model.addAttribute("karyawan", karyawan);
        return "form-add-karyawan";
    }

    @PostMapping(value = "/karyawan/tambah", params = {"hapusRowSerfikasi"})
    private String deleteRowSertifikasi(@ModelAttribute Karyawan karyawan, Model model, @RequestParam("hapusRowSerfikasi") Integer row) {
        temporarysertifikasi = karyawan.getListSertifikasiKaryawan();
        if(karyawan.getListSertifikasiKaryawan().size() != 1){
            temporarysertifikasi.remove(row.intValue());
        }
        karyawan.setListSertifikasiKaryawan(temporarysertifikasi);
        temporarysertifikasi = new ArrayList<>();
        List<Sertifikasi> listofsertifikasiExist = sertifikasiService.getListofSertifikasi();
        model.addAttribute("listofsertifikasiexist", listofsertifikasiExist);
        model.addAttribute("karyawan", karyawan);
        return "form-add-karyawan";
    }


    @GetMapping("karyawan/{id}")
    public String getKaryawanPage(@PathVariable String id, Model model) {
        Karyawan karyawan = karyawanService.getKaryawanbyId(id); // kalau ga ada, returnnya null
        System.out.println(karyawan.getIdKaryawan());
        List<SertifikasiKaryawan> listsertifikasikaryawan = karyawan.getListSertifikasiKaryawan();
        model.addAttribute("karyawan", karyawan);
        model.addAttribute("listSertifikasi", listsertifikasikaryawan);
        return "detail-karyawan";
    }

    @GetMapping(value = "karyawan/{id}/ubah")
    public String updateKaryawanFormPage(@PathVariable String id, Model model) {
        Karyawan karyawan = karyawanService.getKaryawanbyId(id);
//        temporarysertifikasi = karyawan.getListSertifikasiKaryawan();
        List<Sertifikasi> listofsertifikasiExist = sertifikasiService.getListofSertifikasi();
        model.addAttribute("listofsertifikasiexist", listofsertifikasiExist);
        model.addAttribute("karyawan", karyawan);
        return "form-update-karyawan";

    }

    @PostMapping(value = "karyawan/{id}/ubah", params = {"addRowSertifikasiUpdate"})
    public String updateKaryawanAddRow(@ModelAttribute Karyawan karyawan, Model model, @PathVariable String id) {
        SertifikasiKaryawan sertifikasikaryawan = new SertifikasiKaryawan();
        sertifikasikaryawan.setKaryawan(karyawan); // masih disnini
        sertifikasikaryawan.setNoSertifikasi("dfdfdfsfd"); // masih disini
        karyawan.getListSertifikasiKaryawan().add(sertifikasikaryawan);


        List<Sertifikasi> listofsertifikasiExist = sertifikasiService.getListofSertifikasi();
        model.addAttribute("listofsertifikasiexist", listofsertifikasiExist);
        model.addAttribute("karyawan", karyawan);
        return "form-update-karyawan";
    }

    @PostMapping(value = "karyawan/{id}/ubah", params = {"hapusRowSerfikasiUpdate"})
    public String updateKaryawanDeleteRow(@ModelAttribute Karyawan karyawan, Model model, @RequestParam("hapusRowSerfikasiUpdate") Integer row){
        temporarysertifikasi = karyawan.getListSertifikasiKaryawan();
        SertifikasiKaryawan deleted = temporarysertifikasi.get(row.intValue());
        System.out.println(deleted.getIdSertifikasiKaryawan());
        if(karyawan.getListSertifikasiKaryawan().size() != 1){
            karyawan.getListSertifikasiKaryawan().remove(deleted);

//            sertifikasiKaryawanService.deleteSertifikasiKaryawan( );
            deleted.setKaryawan(null);
            deleted.setSertifikasi(null);

            for(SertifikasiKaryawan var : karyawan.getListSertifikasiKaryawan()){
                var.getSertifikasi().getListSertifikasiKaryawan().remove(deleted);
            }
            temporarysertifikasi.remove(deleted);
            sertifikasiKaryawanService.deleteSertifikasiKaryawan(deleted);
        }

        karyawan.setListSertifikasiKaryawan(null);
        karyawan.setListSertifikasiKaryawan(temporarysertifikasi);

        temporarysertifikasi = new ArrayList<>();
        List<Sertifikasi> listofsertifikasiExist = sertifikasiService.getListofSertifikasi();
        model.addAttribute("listofsertifikasiexist", listofsertifikasiExist);
        model.addAttribute("karyawan", karyawan);
        return "form-update-karyawan";
    }

    @PostMapping(value = "karyawan/{id}/ubah", params = {"updateForm"})
    public String updateKaryawanSubmit(@ModelAttribute Karyawan karyawan, Model model){
//        for(SertifikasiKaryawan var : )
        karyawan.setJenisKelamin("0");
        temporarysertifikasi = karyawan.getListSertifikasiKaryawan();
        karyawan.setListSertifikasiKaryawan(null);
        for(SertifikasiKaryawan var : temporarysertifikasi){
            var.setKaryawan(karyawan);
//            var.setNoSertifikasi(sertifikasiKaryawanService.generateSertifikasiId(karyawan.getTanggalLahir(),var.getTanggalPengambilan(), var.getSertifikasi().getNama(),karyawan.getNamaDepan(), karyawan.getIdKaryawan()));
            var.setNoSertifikasi("HFJDSFKDFJSF");
//            if(temporary == null || temporary.size() == 0){
//                temporary =  new ArrayList<>();
//                temporary.add(var);
//                var.getSertifikasi().setListSertifikasiKaryawan(temporary);
//            }
//            else{
//                var.getSertifikasi().getListSertifikasiKaryawan().add(var);
//            }
        }

        karyawan.setListSertifikasiKaryawan(temporarysertifikasi);
        System.out.println(karyawan.getListSertifikasiKaryawan().size());
        System.out.println("^");
        temporarysertifikasi = new ArrayList<>();
        karyawanService.updateKaryawan(karyawan);
//        Karyawan kar = karyawanService.updateKaryawan(karyawan);
        model.addAttribute("namaKaryawan", karyawan.getNamaDepan() + " " + karyawan.getNamaBelakang());
        return "update-karyawan";
    }



    @PostMapping(value = "karyawan/{id}/hapus", params = {"idKaryawanDihapus"})
    public String deleteKaryawan(@RequestParam("idKaryawanDihapus") String id, Model model) {
        Karyawan karyawan = karyawanService.getKaryawanbyId(id);
        karyawanService.deleteKaryawan(karyawan);
        model.addAttribute("namaKaryawan", karyawan.getNamaDepan() + " " + karyawan.getNamaBelakang());
        return "delete-karyawan";
    }


//    @GetMapping
}
