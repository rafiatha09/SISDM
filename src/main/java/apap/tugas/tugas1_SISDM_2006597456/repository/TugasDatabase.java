package apap.tugas.tugas1_SISDM_2006597456.repository;

import apap.tugas.tugas1_SISDM_2006597456.model.Tugas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TugasDatabase extends JpaRepository<Tugas,Integer>{
}
