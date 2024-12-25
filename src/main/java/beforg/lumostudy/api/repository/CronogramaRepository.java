package beforg.lumostudy.api.repository;

import beforg.lumostudy.api.domain.cronograma.Cronograma;
import beforg.lumostudy.api.domain.registro.Rees;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CronogramaRepository extends JpaRepository<Cronograma, String> {
    @Query("SELECT c FROM Cronograma c WHERE c.conta.cod= :cod")
    List<Cronograma> findByContaCod(String cod);
    Cronograma findByCod(String cod);
    @Query("SELECT c FROM Cronograma c WHERE c.data = :data AND c.conta.cod = :codConta")
    Page<Cronograma> findByData(String codConta, String data, Pageable pageable);
    @Query("SELECT c FROM Cronograma c WHERE c.data  < :data AND c.conta.cod = :codConta AND c.concluido = false")
    Page<Cronograma> findByDataBefore(String codConta, String data, Pageable pageable);
}
