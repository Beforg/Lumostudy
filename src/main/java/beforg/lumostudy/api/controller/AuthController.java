package beforg.lumostudy.api.controller;

import beforg.lumostudy.api.domain.user.AuthDTO;
import beforg.lumostudy.api.domain.user.LoginResponseDTO;
import beforg.lumostudy.api.domain.user.RegistroDTO;
import beforg.lumostudy.api.infra.security.TokenService;
import beforg.lumostudy.api.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private ContaService service;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthDTO dto) {
        return ResponseEntity.ok(this.service.login(dto));
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody RegistroDTO dto) {
        this.service.registro(dto);
        return ResponseEntity.ok().build();
    }


}
