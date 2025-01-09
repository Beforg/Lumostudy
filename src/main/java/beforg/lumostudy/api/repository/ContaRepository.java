package beforg.lumostudy.api.repository;

import beforg.lumostudy.api.domain.user.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface ContaRepository  extends JpaRepository<Conta, String> {
    Optional<UserDetails> findByEmail(String email);
    Conta findByCod(String cod);
    @Query("SELECT c FROM Conta c WHERE c.activationCode = :cod")
    Optional<Conta> findByActivationCode(String cod);
}
