package beforg.lumostudy.api.service;

import beforg.lumostudy.api.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    ContaRepository contaRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserDetails> searchConta = this.contaRepository.findByEmail(email);
        if (searchConta.isEmpty()) {
            throw new UsernameNotFoundException("Conta não encontrada");
        }
        return searchConta.get();
    }
}
