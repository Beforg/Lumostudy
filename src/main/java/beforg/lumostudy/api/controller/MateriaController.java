package beforg.lumostudy.api.controller;

import beforg.lumostudy.api.domain.materia.AtualizaMateriaDTO;
import beforg.lumostudy.api.domain.materia.CadastroMateriaDTO;
import beforg.lumostudy.api.domain.materia.ExcluiMateriaDTO;
import beforg.lumostudy.api.domain.materia.MateriaDTO;
import beforg.lumostudy.api.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @PostMapping("/cadastrar/{cod}")
    public ResponseEntity cadastrarMateria(@RequestBody CadastroMateriaDTO dto, @PathVariable String cod) {
         this.materiaService.cadastrarMateria(dto, cod);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listar/{cod}")
    public ResponseEntity listarMaterias(@PathVariable String cod) {
        return ResponseEntity.ok(this.materiaService.listarMaterias(cod));
    }

    @GetMapping("/listar/{cod}/page")
    public ResponseEntity<Page<MateriaDTO>> listarPorPagina(
            @PathVariable String cod,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "6") int size) {
        return ResponseEntity.ok(this.materiaService.listarMateriasPorPagina(cod, page, size));
    }

    @DeleteMapping("/excluir/{cod}")
    public ResponseEntity excluirMateria(@PathVariable String cod) {
        this.materiaService.excluirMateria(cod);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/editar")
    public ResponseEntity atualizarMateria(@RequestBody AtualizaMateriaDTO dto) {
        this.materiaService.atualizarMateria(dto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/categorias/{cod}")
    public ResponseEntity<List<String>> categoriasMaterias(@PathVariable String cod) {
        return ResponseEntity.ok(this.materiaService.categoriasMaterias(cod));
    }
}
