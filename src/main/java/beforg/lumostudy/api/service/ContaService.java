package beforg.lumostudy.api.service;

import beforg.lumostudy.api.domain.user.*;
import beforg.lumostudy.api.domain.response.LoginResponseDTO;
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
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ReesService reesService;
    @Autowired
    private EmailService emailService;
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
        double pontuacao = this.reesService.calcularPontuacao(conta.getCod());
        String token  = this.tokenService.generateToken(conta);
        return new LoginResponseDTO(token, conta.getCod(), conta.getEmail(), conta.getNome(), conta.getUserNickName(), pontuacao, conta.getDataCriacao());
    }

    public void registro(RegistroDTO dto) {
        if(this.contaRepository.findByEmail(dto.email()).isPresent()) {
            throw new EmailExistenteException("Email já cadastrado");
        }
        String senhaCriptografada = new BCryptPasswordEncoder().encode(dto.senha());
        Conta conta = new Conta(dto.email(), senhaCriptografada, dto.nome(), dto.userNickName());
        conta.setActivationCode(UUID.randomUUID().toString());
        emailService.sendEmail(conta.getEmail(), conta.getActivationCode());
        this.contaRepository.save(conta);
    }

    public void uploadFoto(String cod, MultipartFile foto) {
        Conta conta = this.contaRepository.findByCod(cod);
        if (conta == null) {
            throw new ContaNotFoundException("Conta não encontrada");
        }
        try {
            String ext = Objects.requireNonNull(foto.getOriginalFilename()).substring(foto.getOriginalFilename().lastIndexOf("."));
            String fileName = cod + ext;
            Path filePath = Paths.get(photoStoragePath, fileName);
            Files.write(filePath, foto.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar foto", e);
        }
    }

    public void activate(String cod) {
      Optional<Conta>  conta = this.contaRepository.findByActivationCode(cod);
        if (conta.isEmpty()) {
            throw new ContaNotFoundException("Código de ativação inválido");
        }
        conta.get().ativarConta();
        this.contaRepository.save(conta.get());
    }

    public void update(UpdateContaDTO dto, String cod) {
        Conta conta = this.contaRepository.findByCod(cod);
        if (conta == null) {
            throw new ContaNotFoundException("Conta não encontrada");
        }
        if (!new BCryptPasswordEncoder().matches(dto.password(), conta.getSenha())) {
            throw new InvalidPasswordException("Senha inválida");
        }
        UpdateType type = UpdateType.fromValue(dto.type());
        switch (type) {
            case PASSWORD:
                conta.setSenha(new BCryptPasswordEncoder().encode(dto.newValue()));
                break;
            case EMAIL:
                conta.setEmail(dto.newValue());
                break;
            case NAME:
                conta.setNome(dto.newValue());
                break;
            case USERNAME:
                conta.setUserNickName(dto.newValue());
                break;
        }
        contaRepository.save(conta);
    }

    public void delete(String cod) {
        Conta conta = this.contaRepository.findByCod(cod);
        if (conta == null) {
            throw new ContaNotFoundException("Conta não encontrada");
        }
        conta.setAtivo(false);
        contaRepository.save(conta);
    }

    public void recuperarSenha(RecuperacaoSenhaDTO dto) {
        Optional<UserDetails> emailConta = this.contaRepository.findByEmail(dto.email());
        if (emailConta.isEmpty()) {
            throw new ContaNotFoundException("Email não encontrada");
        }
        Conta conta = (Conta) emailConta.get();
        String resetToken = UUID.randomUUID().toString();
        conta.setTokenRecuperacao(resetToken);
        this.contaRepository.save(conta);
        emailService.recuperarSenha(conta.getEmail(), resetToken);
    }

    public void resetPassword(String tokenRecuperacao, ResetarSenhaDTO dto) {
        Conta conta = this.contaRepository.findByTokenRecuperacao(tokenRecuperacao);
        if (conta == null) {
            throw new ContaNotFoundException("Token de recuperação inválido");
        }

        conta.setSenha(new BCryptPasswordEncoder().encode(dto.senha()));
        conta.setTokenRecuperacao(null);
        this.contaRepository.save(conta);
    }
}
