package beforg.lumostudy.api.repository;

import beforg.lumostudy.api.domain.user.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface ContaRepository  extends JpaRepository<Conta, String> {
    UserDetails findByEmail(String email);
}
