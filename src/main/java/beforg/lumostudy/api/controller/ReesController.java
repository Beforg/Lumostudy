package beforg.lumostudy.api.controller;

import beforg.lumostudy.api.domain.registro.*;
import beforg.lumostudy.api.service.ReesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rees")
public class ReesController {

    @Autowired
    private ReesService reesService;

    @PostMapping("/cadastrar/{cod}")
    public ResponseEntity cadastrar(@RequestBody CadastroReesDTO dto, @PathVariable String cod) {
        this.reesService.registrarEstudo(dto, cod);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listar/{cod}/page")
    public ResponseEntity<Page<ReesDTO>> listarRegistros(@PathVariable String cod,
                                                         @RequestParam(defaultValue = "1") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.reesService.listarRegistros(cod, page, size));
    }

    @GetMapping("/conteudo/{cod}")
    public ResponseEntity<List<String>> listarConteudos(@PathVariable String cod) {
        return ResponseEntity.ok(this.reesService.getConteudoByMateria(cod));
    }


    @DeleteMapping("/excluir/{cod}")
    public ResponseEntity excluirEstudo(@PathVariable String cod) {
        this.reesService.excluirEstudo(cod);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/editar")
    public ResponseEntity editarEstudo(@RequestBody AtualizaReesDTO dto) {
        this.reesService.editarEstudo(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listar/{cod}/{data}/page")
    public ResponseEntity listarRegistroPorData(@PathVariable String cod, @PathVariable String data,
                                                @RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(this.reesService.listarRegistrosPorData(cod, data, page, size));
    }

}
