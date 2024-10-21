package beforg.lumostudy.api.repository;

import beforg.lumostudy.api.domain.materia.Materia;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MateriaRepository extends JpaRepository<Materia, String> {
    List<Materia> findByContaCod(String contaCod);
    @Modifying
    @Transactional
    @Query("DELETE FROM Materia m WHERE m.cod = :cod")
    void deleteByCod(String cod);
}
