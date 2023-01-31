package apap.tugas.tugas1_SISDM_2006597456.controller;

import apap.tugas.tugas1_SISDM_2006597456.model.*;
import apap.tugas.tugas1_SISDM_2006597456.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.SysexMessage;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class TugasController {
    @Qualifier("presensiServiceImpl")
    @Autowired
    private PresensiServiceImpl presensiService;

    @Qualifier("karyawanServiceImpl")
    @Autowired
    private KaryawanService karyawanService;

    @Qualifier("tugasServiceImpl")
    @Autowired
    private TugasService tugasService;

    @GetMapping("/tambah-tugas")
    public String addTugasForm(Model model) {
        Tugas tugas = new Tugas();
        model.addAttribute("tugas", tugas);
        return "form-add-tugas";
    }

    @PostMapping("/tambah-tugas")
    public String addTugas(@ModelAttribute Tugas tugas, Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = "2000-12-20";

//        Presensi presensi = new Presensi();
//        tugas.setPresensi(presensi);
//        System.out.println(tugas.getIdTugas());
//        System.out.println(tugas.getJudulTugas());
//        System.out.println(tugas.getDeskripsiTugas());
//        System.out.println(tugas.getPoint());
//        System.out.println(tugas.getStatus());
//        System.out.println(tugas.getPresensi());
        tugasService.addTugas(tugas);
        model.addAttribute("tugas", tugas);
        return "add-tugas";
    }

    //    @GetMapping("/filter-tugas")
//    public String filterTugasForm(Model model){
//        List<Karyawan> listofkaryawanExist = karyawanService.getListofKaryawan();
//        model.addAttribute("listofkaryawanExist", listofkaryawanExist);
//        List<Tugas> listofTugasExist = tugasService.getListofExistTugas();
//        model.addAttribute("listoftugasExist", listofTugasExist);
//        return "filter-tugas";
//    }
    @GetMapping("/filter-tugas")
    public String filterTugasFormPage(Model model) {
        List<Karyawan> listKaryawan = karyawanService.getListofKaryawan();
        List<Tugas> listTugas = tugasService.getListofExistTugas();
        System.out.println(listTugas.size());
        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("listTugas", listTugas);
        return "filter-tugas";
    }

    @GetMapping(value = "/filter-tugas", params = {"filterKaryawan"})
    public String filterTugasSubmitPage(@RequestParam(value = "id-karyawan", required = true) String id_karyawan, @RequestParam(value = "status", required = true) String status,
                                        Model model) {
        List<Karyawan> listKaryawan = karyawanService.getListofKaryawan();
        Karyawan karyawan = karyawanService.getKaryawanbyId(id_karyawan);
        List<Tugas> listTugas = tugasService.getListofExistTugas();
        List<Tugas> listTugasBaru = new ArrayList<>();

        for (int j = 0; j < listTugas.size(); j++) {
            Karyawan karyawanTugas = listTugas.get(j).getPresensi().getKaryawan();
            if ((listTugas.get(j).getStatus() == Integer.parseInt(status)) && (karyawanTugas.getIdKaryawan() == karyawan.getIdKaryawan())) {
                listTugasBaru.add(listTugas.get(j));
            }
        }

        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("listTugas", listTugasBaru);
        return "filter-tugas";
    }
}
