package beforg.lumostudy.api.controller;

import beforg.lumostudy.api.domain.estatisticas.EstatisticaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController {

    @GetMapping("/{periodo}")
    public ResponseEntity<EstatisticaDTO> listarEstatisticas(@RequestParam String periodo) {
        //receber o periodo e devolver Tempo total  e sessoes.
        return ResponseEntity.ok(new EstatisticaDTO("Alfa",10, 5));
    }
    @GetMapping("/material/{cod}")
        public ResponseEntity<EstatisticaDTO>  listarEstatisticasMaterial(@RequestParam String cod) {
            // receber o código da materia e devolver o tempo total e sessoes.
        return ResponseEntity.ok(new EstatisticaDTO("Alfa",10, 5));
        }

    @GetMapping("/conteudo/{cod}")
        public ResponseEntity<EstatisticaDTO>  listarEstatisticasMateria(@RequestParam String cod) {
        // receber o código da materia  e devolver o tempo total e sessoes.
        return ResponseEntity.ok(new EstatisticaDTO("Alfa",10, 5));
    }

}
