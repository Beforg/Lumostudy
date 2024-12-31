package beforg.lumostudy.api.repository;

import beforg.lumostudy.api.domain.registro.Rees;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReesRepository extends JpaRepository<Rees, String> {
    @Query("SELECT r FROM Rees r WHERE r.conta.cod = :codConta ORDER BY r.data ASC")
    Page<Rees> findByContaCod(String codConta, Pageable pageable);
    @Query("SELECT r FROM Rees r WHERE r.conta.cod = :cod")
    List<Rees> findAllByContaCod(String cod);
    @Transactional
    @Modifying
    @Query("DELETE FROM Rees r WHERE r.cod = :codRegistro")
    void deleteByCod(String codRegistro);
    @Query("SELECT r.conteudo FROM Rees r WHERE r.materia.cod = :codMateria GROUP BY r.conteudo")
    List<String> findByMateriaCod(String codMateria);
    @Query("SELECT COUNT(r) FROM Rees r WHERE r.materia.cod = :codMateria")
    int countByMateriaCod(String codMateria);
    @Query("SELECT r FROM Rees r WHERE r.data = :data AND r.conta.cod = :codConta" )
    Page<Rees> findByData(String codConta, String data, Pageable pageable);
    @Transactional
    @Modifying
    @Query("DELETE FROM Rees r WHERE r.materia.cod = :cod")
    void deleteAllByMateriaCod(String cod);
}