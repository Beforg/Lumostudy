package beforg.lumostudy.api.repository;

import beforg.lumostudy.api.domain.registro.RegistroEstudo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistroEstudoRepository extends JpaRepository<RegistroEstudo, String> {
    @Query("SELECT r FROM RegistroEstudo r WHERE r.conta.cod = :codConta")
    List<RegistroEstudo> findByContaCod(String codConta);
    @Transactional
    @Modifying
    @Query("DELETE FROM RegistroEstudo r WHERE r.cod = :codRegistro")
    void deleteByCod(String codRegistro);
}
