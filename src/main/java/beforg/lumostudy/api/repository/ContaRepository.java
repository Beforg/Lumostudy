package beforg.lumostudy.api.repository;

import beforg.lumostudy.api.domain.registro.Rees;
import beforg.lumostudy.api.domain.user.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface ContaRepository  extends JpaRepository<Conta, String> {
    Optional<UserDetails> findByEmail(String email);
    Conta findByCod(String cod);
}
