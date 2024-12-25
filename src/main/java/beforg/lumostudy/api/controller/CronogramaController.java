package beforg.lumostudy.api.controller;

import beforg.lumostudy.api.domain.cronograma.CadastroCronogramaDTO;
import beforg.lumostudy.api.domain.cronograma.ConcluidoCronogramaDTO;
import beforg.lumostudy.api.domain.cronograma.CronogramaDTO;
import beforg.lumostudy.api.domain.registro.ReesDTO;
import beforg.lumostudy.api.service.CronogramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cronograma")
public class CronogramaController {

    @Autowired
    private CronogramaService service;

    @PostMapping("/cadastrar/{cod}")
    public ResponseEntity cadastrar(@RequestBody CadastroCronogramaDTO dto, @PathVariable String cod) {
        this.service.cadastrar(dto, cod);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/listar/{cod}")
    public ResponseEntity listar(@PathVariable String cod) {
        return ResponseEntity.ok(this.service.listar(cod));
    }
    @PutMapping("/editar/{cod}")
    public ResponseEntity editar(@PathVariable String cod, @RequestBody CronogramaDTO dto) {
        this.service.editar(dto, cod);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/concluir")
    public ResponseEntity concluir(@RequestBody ConcluidoCronogramaDTO dto) {
        this.service.concluir(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listar/daily/{cod}/page")
    public ResponseEntity<Page<CronogramaDTO>> listarCronogramaHoje(@PathVariable String cod,
                                                             @RequestParam(defaultValue = "1") int page,
                                                             @RequestParam(defaultValue = "4") int size) {
        return ResponseEntity.ok(this.service.listarRegistrosHoje(cod, page, size));
    }

    @GetMapping("/listar/atrasados/{cod}/page")
    public ResponseEntity<Page<CronogramaDTO>> listarCronogramaAtrasados(@PathVariable String cod,
                                                                    @RequestParam(defaultValue = "1") int page,
                                                                    @RequestParam(defaultValue = "4") int size) {
        return ResponseEntity.ok(this.service.listarRegistrosAtrasados(cod, page, size));
    }

    @DeleteMapping("/excluir/{cod}")
    public ResponseEntity excluir(@PathVariable String cod) {
        this.service.excluir(cod);
        return ResponseEntity.ok().build();
    }
}
