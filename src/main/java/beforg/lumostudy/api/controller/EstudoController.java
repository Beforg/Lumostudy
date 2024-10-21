package beforg.lumostudy.api.controller;

import beforg.lumostudy.api.domain.registro.AtualizaRegistroEstudoDTO;
import beforg.lumostudy.api.domain.registro.CadastroRegistroEstudoDTO;
import beforg.lumostudy.api.domain.registro.ExcluiRegistroEstudoDTO;
import beforg.lumostudy.api.service.RegistroEstudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estudo")
public class EstudoController {

    @Autowired
    private RegistroEstudoService registroEstudoService;

    @PostMapping("/registrar")
    public ResponseEntity registrarEstudo(@RequestBody CadastroRegistroEstudoDTO dto) {
        this.registroEstudoService.registrarEstudo(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/excluir")
    public ResponseEntity excluirEstudo(@RequestBody ExcluiRegistroEstudoDTO dto) {
        this.registroEstudoService.excluirEstudo(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listar")
    public ResponseEntity listarEstudos() {
        return ResponseEntity.ok(this.registroEstudoService.listarRegistros());
    }

    @PutMapping("/editar")
    public ResponseEntity editarEstudo(@RequestBody AtualizaRegistroEstudoDTO dto) {
        this.registroEstudoService.editarEstudo(dto);
        return ResponseEntity.ok().build();
    }

}
