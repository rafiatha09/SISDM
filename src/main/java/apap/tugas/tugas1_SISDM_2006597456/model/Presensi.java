package apap.tugas.tugas1_SISDM_2006597456.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.asm.Advice;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

//Entitas PRESENSI merepresentasikan kehadiran karyawan yang dapat tercatat di
//sistem
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "presensi")
public class Presensi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPresensi;

    @NotNull
    @Column(name = "status", nullable = false)
    private int status;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggal; // tanggal presensi

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime waktuMasuk;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime waktuKeluar;

    // ke karyawan
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name ="id_karyawan", referencedColumnName = "idKaryawan", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Karyawan karyawan;

    // ke tugas
    @OneToMany(mappedBy = "presensi", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Tugas> listTugas;
}
