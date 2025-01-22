package beforg.lumostudy.api.controller;

import beforg.lumostudy.api.domain.response.ResponseDTO;
import beforg.lumostudy.api.domain.user.AuthDTO;
import beforg.lumostudy.api.domain.response.LoginResponseDTO;
import beforg.lumostudy.api.domain.user.RecuperacaoSenhaDTO;
import beforg.lumostudy.api.domain.user.RegistroDTO;
import beforg.lumostudy.api.domain.user.ResetarSenhaDTO;
import beforg.lumostudy.api.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private ContaService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthDTO dto) {
        return ResponseEntity.ok(this.service.login(dto));
    }

    @GetMapping("/activate/{cod}")
    public ResponseEntity<ResponseDTO> activate(@PathVariable String cod) {
        this.service.activate(cod);
        return ResponseEntity.ok(
                new ResponseDTO(
                        "Conta ativada com sucesso",
                        HttpStatus.OK.toString())
        );
    }

    @PostMapping("/registrar")
    public ResponseEntity<ResponseDTO> registrar(@RequestBody RegistroDTO dto) {
        this.service.registro(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDTO(
                        "Usuário registrado com sucesso, email enviado para ativação",
                        HttpStatus.CREATED.toString())
        );
    }
    @PostMapping("/recuperar-senha")
    public ResponseEntity<ResponseDTO> recuperarSenha(@RequestBody RecuperacaoSenhaDTO dto) {
        this.service.recuperarSenha(dto);
        return ResponseEntity.ok(
                new ResponseDTO(
                        "Email para recuperação enviado com sucesso",
                        HttpStatus.OK.toString())
        );
    }
    @PostMapping("/reset-password/{tokenRecuperacao}")
    public ResponseEntity<ResponseDTO> resetPassword(@PathVariable String tokenRecuperacao, @RequestBody ResetarSenhaDTO dto) {
        this.service.resetPassword(tokenRecuperacao, dto);
        return ResponseEntity.ok(
                new ResponseDTO(
                        "Senha redefinida com sucesso",
                        HttpStatus.OK.toString())
        );
    }


}
