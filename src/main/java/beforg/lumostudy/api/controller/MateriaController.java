package beforg.lumostudy.api.controller;

import beforg.lumostudy.api.domain.materia.AtualizaMateriaDTO;
import beforg.lumostudy.api.domain.materia.CadastroMateriaDTO;
import beforg.lumostudy.api.domain.materia.ExcluiMateriaDTO;
import beforg.lumostudy.api.domain.materia.MateriaDTO;
import beforg.lumostudy.api.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarMateria(@RequestBody CadastroMateriaDTO dto) {
        System.out.println("CHAMOU");
         this.materiaService.cadastrarMateria(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listar")
    public ResponseEntity listarMaterias() {
        return ResponseEntity.ok(this.materiaService.listarMaterias());
    }

    @DeleteMapping("/excluir")
    public ResponseEntity excluirMateria(@RequestBody ExcluiMateriaDTO dto) {
        this.materiaService.excluirMateria(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/atualizar")
    public ResponseEntity atualizarMateria(@RequestBody AtualizaMateriaDTO dto) {
        this.materiaService.atualizarMateria(dto);
        return ResponseEntity.ok().build();
    }

}
