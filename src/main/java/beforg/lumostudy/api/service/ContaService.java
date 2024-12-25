package beforg.lumostudy.api.service;

import beforg.lumostudy.api.domain.user.AuthDTO;
import beforg.lumostudy.api.domain.user.Conta;
import beforg.lumostudy.api.domain.user.LoginResponseDTO;
import beforg.lumostudy.api.domain.user.RegistroDTO;
import beforg.lumostudy.api.infra.security.TokenService;
import beforg.lumostudy.api.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private TokenService tokenService;

    public LoginResponseDTO login(AuthDTO dto) {
        Optional<UserDetails> searchConta = this.contaRepository.findByEmail(dto.email());
        if (searchConta.isEmpty()) {
            throw new RuntimeException("Erro");
        }
        Conta conta = (Conta) searchConta.get();
        if (! new BCryptPasswordEncoder().matches(dto.senha(), conta.getPassword())) {
            throw new RuntimeException("Erro");
        }
        String token  = this.tokenService.generateToken(conta);
        return new LoginResponseDTO(token, conta.getCod(), conta.getEmail(), conta.getNome());
    }

    public void registro(RegistroDTO dto) {
        if(this.contaRepository.findByEmail(dto.email()).isPresent()) {
            throw new RuntimeException("Erro");
        }
        String senhaCriptografada = new BCryptPasswordEncoder().encode(dto.senha());
        Conta conta = new Conta(dto.email(), senhaCriptografada, dto.nome());
        this.contaRepository.save(conta);
    }
}
