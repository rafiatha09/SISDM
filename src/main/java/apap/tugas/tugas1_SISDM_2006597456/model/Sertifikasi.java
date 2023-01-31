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
import java.util.List;

// Entitas SERTIFIKASI merupakan detail sertifikat pelatihan atau keahlian yang dimiliki
//oleh masing-masing karyawan PT. SuperFood
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sertifikasi")
public class Sertifikasi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSertifikasi;

    @NotNull
    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String nama;

    // ke sertifikasi
    @OneToMany(mappedBy = "sertifikasi", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SertifikasiKaryawan> listSertifikasiKaryawan;
}
