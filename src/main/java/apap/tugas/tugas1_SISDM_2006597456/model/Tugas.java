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
// Entitas TUGAS merupakan detail pekerjaan yang dimiliki oleh masing-masing
//karyawan
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tugas")
public class Tugas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private int idTugas;

    @NotNull
    @Size(max = 255)
    @Column(name = "judul_tugas", nullable = false)
    private String judulTugas;

    @NotNull
    @Size(max = 255)
    @Column(name = "deskripsi_tugas", nullable = false)
    private String deskripsiTugas;

    @NotNull
    @Column(name = "story_point", nullable = false)
    private int point;

    @NotNull
    @Column(name = "status", nullable = false)
    private int status;

    // ke presensi
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name ="idpresensi", referencedColumnName = "idPresensi")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Presensi presensi;
}
