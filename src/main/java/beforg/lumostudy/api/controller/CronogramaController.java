package beforg.lumostudy.api.controller;

import beforg.lumostudy.api.domain.cronograma.CadastroCronogramaDTO;
import beforg.lumostudy.api.domain.cronograma.ConcluidoCronogramaDTO;
import beforg.lumostudy.api.domain.cronograma.CronogramaDTO;

import beforg.lumostudy.api.domain.response.ResponseDTO;
import beforg.lumostudy.api.service.CronogramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cronograma")
public class CronogramaController {

    @Autowired
    private CronogramaService service;

    @PostMapping("/cadastrar/{cod}")
    public ResponseEntity<ResponseDTO> cadastrar(@RequestBody CadastroCronogramaDTO dto, @PathVariable String cod) {
        this.service.cadastrar(dto, cod);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDTO(
                        "Item cadastrado com sucesso no cronograma",
                        HttpStatus.CREATED.toString()));
    }
    @GetMapping("/listar/{cod}")
    public ResponseEntity<List<CronogramaDTO>> listar(@PathVariable String cod) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.listar(cod));
    }
    @PutMapping("/editar/{cod}")
    public ResponseEntity<ResponseDTO> editar(@PathVariable String cod, @RequestBody CronogramaDTO dto) {
        this.service.editar(dto);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDTO(
                        "Item editado com sucesso",
                        HttpStatus.OK.toString()));
    }

    @PutMapping("/concluir")
    public ResponseEntity<ResponseDTO> concluir(@RequestBody ConcluidoCronogramaDTO dto) {
        this.service.concluir(dto);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDTO(
                        "Estado do item alterado com sucesso" + dto.concluido(),
                        HttpStatus.OK.toString()));
    }

    @GetMapping("/listar/daily/{cod}/page")
    public ResponseEntity<Page<CronogramaDTO>> listarCronogramaHoje(@PathVariable String cod,
                                                             @RequestParam(defaultValue = "1") int page,
                                                             @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(this.service.listarRegistrosHoje(cod, page, size));
    }

    @GetMapping("/listar/{cod}/{data}/page")
    public ResponseEntity<Page<CronogramaDTO>> listarCronogramaPorData(@PathVariable String cod,
                                                                 @PathVariable String data,
                                                                 @RequestParam(defaultValue = "1") int page,
                                                                 @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(this.service.listarRegistrosPorData(cod, data, page, size));
    }

    @GetMapping("/listar/atrasados/{cod}/page")
    public ResponseEntity<Page<CronogramaDTO>> listarCronogramaAtrasados(@PathVariable String cod,
                                                                    @RequestParam(defaultValue = "1") int page,
                                                                    @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(this.service.listarRegistrosAtrasados(cod, page, size));
    }

    @DeleteMapping("/excluir/{cod}")
    public ResponseEntity<ResponseDTO> excluir(@PathVariable String cod) {
        this.service.excluir(cod);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDTO(
                        "Item excluído com sucesso",
                        HttpStatus.OK.toString()));
    }
}
