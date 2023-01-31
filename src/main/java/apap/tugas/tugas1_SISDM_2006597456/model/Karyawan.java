package apap.tugas.tugas1_SISDM_2006597456.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;

//Entitas KARYAWAN merepresentasikan karyawan yang terdaftar pada PT. Superfood.
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "karyawan")
public class Karyawan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private int idKaryawan;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama_depan", nullable = false)
    private String namaDepan;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama_belakang", nullable = false)
    private String namaBelakang;

    @NotNull
    @Size(max = 10)
    @Column(name = "jenis_kelamin", nullable = false)
    private String jenisKelamin;

    @NotNull
    @Column(name = "tanggal_lahir", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalLahir;

    @NotNull
    @Column(name = "email", nullable = false)
    @Size(max = 255)
    private String email;

    // ke presensi
    @OneToMany(mappedBy = "karyawan", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // mapped, minta nama variable yang disana
    private List<Presensi> listPresensi;

    // ke sertifikasi karyawan
    @OneToMany(mappedBy = "karyawan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SertifikasiKaryawan> listSertifikasiKaryawan;

}
