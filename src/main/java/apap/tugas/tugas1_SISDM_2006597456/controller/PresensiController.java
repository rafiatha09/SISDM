package apap.tugas.tugas1_SISDM_2006597456.controller;


import apap.tugas.tugas1_SISDM_2006597456.model.*;
import apap.tugas.tugas1_SISDM_2006597456.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PresensiController {

    List<Tugas> arraytugas = new ArrayList<>();
    List<Tugas> arraytugasupdate = new ArrayList<>();
    List<Tugas> tugasUpdate = new ArrayList<>();
    @Qualifier("presensiServiceImpl")
    @Autowired
    private PresensiServiceImpl presensiService;

    @Qualifier("karyawanServiceImpl")
    @Autowired
    private KaryawanService karyawanService;

    @Qualifier("tugasServiceImpl")
    @Autowired
    private TugasService tugasService;

    @Qualifier("sertifikasiServiceImpl")
    @Autowired
    private SertifikasiService sertifikasiService;

    @GetMapping("presensi/")
    public String getListPresensiAll(Model model) {
        List<Presensi> listpresensi = presensiService.getListofPresensi();
        model.addAttribute("listpresensi", listpresensi);
        return "all-presensi";
    }

    @GetMapping("presensi/tambah")
    public String addPresensiForm(Model model) {
        List<Karyawan> listofkaryawan = karyawanService.getListofKaryawan();
        List<Tugas> listoftugas = tugasService.getListofExistTugas();
        Presensi presensi = new Presensi();
        Tugas tugas = new Tugas();

        arraytugas.add(tugas);
        presensi.setListTugas(arraytugas);
        arraytugas = new ArrayList<>();

        model.addAttribute("presensi", presensi);
        model.addAttribute("listTugasexist", listoftugas);
        model.addAttribute("listkaryawanexist", listofkaryawan);
        return "form-add-presensi";
    }

    @PostMapping(value = "presensi/tambah", params = {"submitPresensi"})
    public String submitFormPresensi(@ModelAttribute Presensi presensi, Model model) {
        System.out.println(presensi.getIdPresensi());
        System.out.println("id");
        if (presensi.getWaktuMasuk().isAfter(LocalTime.parse("07:00"))) {
            presensi.setStatus(0); // terlambat
        } else {
            presensi.setStatus(1); // tepat waktu
        }

        List<Tugas> copyofTugas = presensi.getListTugas();
        presensi.setListTugas(null);

        for (Tugas tugas : copyofTugas) {
            Tugas oldTugas = tugasService.getTugasById(tugas.getIdTugas());
            oldTugas.setPresensi(presensi);
            oldTugas.setStatus(tugas.getStatus());
            arraytugas.add(oldTugas);
            System.out.println("-------");
            System.out.println(oldTugas.getIdTugas());
            System.out.println(oldTugas.getJudulTugas());
            System.out.println(oldTugas.getPoint());
            System.out.println(oldTugas.getStatus());
            System.out.println(oldTugas.getDeskripsiTugas());
            System.out.println(oldTugas.getPresensi());
//            tugasService.updateTugas(oldTugas);
        }
        presensiService.addPresensi(presensi);
        arraytugas = new ArrayList<>();
        model.addAttribute("tanggal", presensi.getTanggal());
        model.addAttribute("nama", presensi.getKaryawan().getNamaDepan() + " " + presensi.getKaryawan().getNamaBelakang());
        return "add-presensi";
    }

    @PostMapping(value = "presensi/tambah", params = {"addRowTugas"})
    private String addRowTugas(@ModelAttribute Presensi presensi, Model model) { // buat class tugas baru dengan masukin attribute yang lama
        List<Karyawan> listofkaryawan = karyawanService.getListofKaryawan();
        List<Tugas> listoftugas = tugasService.getListofExistTugas();
        presensi.getListTugas().add(new Tugas());

        model.addAttribute("presensi", presensi);
        model.addAttribute("listTugasexist", listoftugas);
        model.addAttribute("listkaryawanexist", listofkaryawan);
        return "form-add-presensi";
    }

    @PostMapping(value = {"presensi/tambah"}, params = {"hapusRowTugas"})
    private String deleteRowTugas(@ModelAttribute Presensi presensi, Model model, @RequestParam("hapusRowTugas") Integer row) {
        if (presensi.getListTugas().size() != 1) {
            arraytugas = presensi.getListTugas();
            arraytugas.remove(row.intValue());
        }

        List<Karyawan> listofkaryawan = karyawanService.getListofKaryawan();
        List<Tugas> listoftugas = tugasService.getListofExistTugas();

        presensi.setListTugas(arraytugas);
        arraytugas = new ArrayList<>();
        model.addAttribute("presensi", presensi);
        model.addAttribute("listTugasexist", listoftugas);
        model.addAttribute("listkaryawanexist", listofkaryawan);
        return "form-add-presensi";
    }


    @GetMapping("presensi/{id}/ubah")
    public String updateGetPresensi(Model model, @PathVariable String id) {
        Presensi presensi = presensiService.getPresensibyId(Integer.parseInt(id));
        List<Karyawan> listofkaryawan = karyawanService.getListofKaryawan();
        List<Tugas> listoftugas = tugasService.getListofExistTugas();
        model.addAttribute("presensi", presensi);
        model.addAttribute("listTugasexist", listoftugas);
        model.addAttribute("listkaryawanexist", listofkaryawan);
        return "form-update-presensi";

    }

    @PostMapping(value = {"presensi/{id}/ubah"}, params = {"addRowTugasUpdate"})
    private String addRowTugasUpdate(@ModelAttribute Presensi presensi, Model model) { // buat class tugas baru dengan masukin attribute yang lama
        if (presensi.getListTugas() == null || presensi.getListTugas().size() == 0) {
            presensi.setListTugas(new ArrayList<>());
        }
        List<Karyawan> listofkaryawan = karyawanService.getListofKaryawan();
        List<Tugas> listoftugas = tugasService.getListofExistTugas();

        presensi.getListTugas().add(new Tugas());

        model.addAttribute("presensi", presensi);
        model.addAttribute("listTugasexist", listoftugas);
        model.addAttribute("listkaryawanexist", listofkaryawan);
        return "form-update-presensi";
    }

    @PostMapping(value = {"presensi/{id}/ubah"}, params = {"hapusRowUpdate"})
    private String deleteRowTugasUpdate(@ModelAttribute Presensi presensi, Model model, @RequestParam("hapusRowUpdate") Integer row) {
        if (presensi.getListTugas().size() != 1) {
            arraytugasupdate = presensi.getListTugas();
            Tugas tHapus = arraytugasupdate.get(row.intValue());

            arraytugasupdate.remove(row.intValue());
            presensi.setListTugas(arraytugasupdate);
            arraytugasupdate = new ArrayList<>();

            Tugas old = tugasService.getTugasById(tHapus.getIdTugas());
            old.setPresensi(null);
            tugasService.updateTugas(old);
        }

        List<Karyawan> listofkaryawan = karyawanService.getListofKaryawan();
        List<Tugas> listoftugas = tugasService.getListofExistTugas();
        model.addAttribute("presensi", presensi);
        model.addAttribute("listTugasexist", listoftugas);
        model.addAttribute("listkaryawanexist", listofkaryawan);
        return "form-update-presensi";
    }

    @PostMapping(value = {"presensi/{id}/ubah"}, params = {"submitPresensiUpdate"})
    public String submitFormPresensiUpdate(@ModelAttribute Presensi presensi, Model model) {

        if (presensi.getWaktuMasuk().isAfter(LocalTime.parse("07:00"))) {
            presensi.setStatus(0); // terlambat
        } else {
            presensi.setStatus(1); // tepat waktu
        }
        List<Tugas> copyOftugaslist = presensi.getListTugas(); // copy dari gelist tugas
        presensi.setListTugas(null); // null list tugas
        for (Tugas tugas : copyOftugaslist) {
            Tugas oldTugas = tugasService.getTugasById(tugas.getIdTugas()); // ngambil dari class tugas
            oldTugas.setPresensi(presensi);
            oldTugas.setStatus(tugas.getStatus());
            tugasService.updateTugas(oldTugas);
            tugasUpdate.add(oldTugas);
        }
        System.out.println(presensi.getIdPresensi());
        presensiService.updatePresensi(presensi); // update presensi biar ke save di db
        arraytugasupdate = new ArrayList<>();

        model.addAttribute("tanggal", presensi.getTanggal());
        model.addAttribute("nama", presensi.getKaryawan().getNamaDepan() + " " + presensi.getKaryawan().getNamaBelakang());
        return "update-presensi";
    }

    @GetMapping("presensi/{id}")
    public String detailPresensi(@PathVariable String id, Model model) {
        Presensi presensi = presensiService.getPresensibyId(Integer.parseInt(id));
        List<Tugas> listoftugas = presensi.getListTugas();
//        model.addAttribute('presensi', presensi);
        model.addAttribute("pres", presensi);
        model.addAttribute("listofTugas", listoftugas);
        return "detail-presensi";

    }

    @GetMapping("/filter-sertifikasi")
    public String filterSertifikasiFormPage(Model model) {
        List<Karyawan> listKaryawan = karyawanService.getListofKaryawan();
        List<Sertifikasi> listSertifikasi = sertifikasiService.getListofSertifikasi();

        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("listSertifikasi", listSertifikasi);
        return "filter-sertifikasi";
    }

    @GetMapping(value = "/filter-sertifikasi", params = {"filterSertifikasi"})
    public String filterSertifikasiSubmitPage(@RequestParam(value = "id-sertifikasi", required = true) String id_sertifikasi,
                                        Model model) {
        List<Karyawan> listKaryawan = new ArrayList<>();
        List<Sertifikasi> listSertifikasi = sertifikasiService.getListofSertifikasi();

        for (SertifikasiKaryawan sertifikasikar : sertifikasiService.getSertifikasiById(Integer.parseInt(id_sertifikasi)).getListSertifikasiKaryawan()) {
            listKaryawan.add(sertifikasikar.getKaryawan());
        }

        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("listSertifikasi", listSertifikasi);
        return "filter-sertifikasi";
    }
}
