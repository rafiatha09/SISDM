package apap.tugas.tugas1_SISDM_2006597456.repository;

import apap.tugas.tugas1_SISDM_2006597456.model.Karyawan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KaryawanDatabase extends JpaRepository<Karyawan,Integer>{

}
