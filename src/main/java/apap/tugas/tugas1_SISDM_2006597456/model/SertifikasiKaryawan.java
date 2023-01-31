package apap.tugas.tugas1_SISDM_2006597456.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter

@Table(name = "sertifikasi_karyawan")
public class SertifikasiKaryawan implements Serializable {


    //    @EmbeddedId
//    SertifikasiKaryawanKey keyid;
    // ke sertifikasi
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSertifikasiKaryawan;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_sertifikasi", referencedColumnName = "idSertifikasi", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Sertifikasi sertifikasi;
    // ke

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_karyawan", referencedColumnName = "idKaryawan", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Karyawan karyawan;

    @NotNull
    @Column(name = "tanggal_pengambilan", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalPengambilan;

    @NotNull
    @Column(name = "no_sertifikasi", nullable = false)
    @Size(max = 14)
    private String noSertifikasi;
}
