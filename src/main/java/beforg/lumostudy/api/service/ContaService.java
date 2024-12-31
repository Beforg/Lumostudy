package beforg.lumostudy.api.service;

import beforg.lumostudy.api.domain.user.AuthDTO;
import beforg.lumostudy.api.domain.user.Conta;
import beforg.lumostudy.api.domain.response.LoginResponseDTO;
import beforg.lumostudy.api.domain.user.RegistroDTO;
import beforg.lumostudy.api.infra.exception.EmailExistenteException;
import beforg.lumostudy.api.infra.exception.InvalidPasswordException;
import beforg.lumostudy.api.infra.security.TokenService;
import beforg.lumostudy.api.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        Conta conta = (Conta) searchConta.get();
        if (! new BCryptPasswordEncoder().matches(dto.senha(), conta.getPassword())) {
            throw new InvalidPasswordException("Senha  inválida para o usuário");
        }
        String token  = this.tokenService.generateToken(conta);
        return new LoginResponseDTO(token, conta.getCod(), conta.getEmail(), conta.getNome());
    }

    public void registro(RegistroDTO dto) {
        if(this.contaRepository.findByEmail(dto.email()).isPresent()) {
            throw new EmailExistenteException("Email já cadastrado");
        }
        String senhaCriptografada = new BCryptPasswordEncoder().encode(dto.senha());
        Conta conta = new Conta(dto.email(), senhaCriptografada, dto.nome());
        this.contaRepository.save(conta);
    }
}
