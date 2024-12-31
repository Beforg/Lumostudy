package beforg.lumostudy.api.service;

import beforg.lumostudy.api.domain.user.AuthDTO;
import beforg.lumostudy.api.domain.user.Conta;
import beforg.lumostudy.api.domain.response.LoginResponseDTO;
import beforg.lumostudy.api.domain.user.RegistroDTO;
import beforg.lumostudy.api.infra.exception.ContaNotFoundException;
import beforg.lumostudy.api.infra.exception.EmailExistenteException;
import beforg.lumostudy.api.infra.exception.InvalidPasswordException;
import beforg.lumostudy.api.infra.security.TokenService;
import beforg.lumostudy.api.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private TokenService tokenService;

    @Value("${photo.storage.path}")
    private String photoStoragePath;

    @Transactional(readOnly = true)
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
        return new LoginResponseDTO(token, conta.getCod(), conta.getEmail(), conta.getNome(), conta.getUsername());
    }

    public void registro(RegistroDTO dto) {
        if(this.contaRepository.findByEmail(dto.email()).isPresent()) {
            throw new EmailExistenteException("Email já cadastrado");
        }
        String senhaCriptografada = new BCryptPasswordEncoder().encode(dto.senha());
        Conta conta = new Conta(dto.email(), senhaCriptografada, dto.nome());
        this.contaRepository.save(conta);
    }

    public void uploadFoto(String cod, MultipartFile foto)
        Conta conta = this.contaRepository.findByCod(cod);
        if (conta == null) {
            throw new ContaNotFoundException("Conta não encontrada");
        }
        try {
            String fileName = cod + "_" + foto.getOriginalFilename();
            Path filePath = Paths.get(photoStoragePath, fileName);
            Files.write(filePath, foto.getBytes());
            conta.setFoto(fileName.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar foto", e);
        }
        this.contaRepository.save(conta);
    }
}
