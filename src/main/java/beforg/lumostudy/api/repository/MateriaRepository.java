package beforg.lumostudy.api.repository;

import beforg.lumostudy.api.domain.materia.Materia;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @Query("SELECT m FROM Materia m WHERE m.cod = :cod")
    Materia findByCod(String cod);
    @Query("SELECT  m.categoria FROM Materia m WHERE m.conta.cod = :cod GROUP BY m.categoria")
    List<String> findAllCategorias(String cod);
    Page<Materia> findPageByContaCod(String contaCod, Pageable pageable);
}
