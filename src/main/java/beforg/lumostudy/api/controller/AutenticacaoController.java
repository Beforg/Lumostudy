package beforg.lumostudy.api.controller;

import beforg.lumostudy.api.domain.user.AutenticacaoDTO;
import beforg.lumostudy.api.domain.user.Conta;
import beforg.lumostudy.api.domain.user.LoginResponseDTO;
import beforg.lumostudy.api.domain.user.RegistroDTO;
import beforg.lumostudy.api.infra.security.TokenService;
import beforg.lumostudy.api.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AutenticacaoDTO dto) {
        var senhaUsuario  =  new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var auth =  this.authenticationManager.authenticate(senhaUsuario);
        var token = tokenService.generateToken((Conta) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody RegistroDTO dto) {
        if(this.contaRepository.findByEmail(dto.email()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String senhaCriptografada = new BCryptPasswordEncoder().encode(dto.senha());
        Conta conta = new Conta(dto.email(), senhaCriptografada);
        this.contaRepository.save(conta);
        return ResponseEntity.ok().build();
    }


}
