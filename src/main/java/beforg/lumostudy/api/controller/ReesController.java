package beforg.lumostudy.api.controller;

import beforg.lumostudy.api.domain.registro.*;
import beforg.lumostudy.api.domain.response.ResponseDTO;
import beforg.lumostudy.api.service.ReesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rees")
public class ReesController {

    @Autowired
    private ReesService reesService;

    @PostMapping("/cadastrar/{cod}")
    public ResponseEntity<ResponseDTO> cadastrar(@RequestBody CadastroReesDTO dto, @PathVariable String cod) {
        this.reesService.registrarEstudo(dto, cod);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDTO(
                        "Registro de estudo registrado com sucesso",
                        HttpStatus.CREATED.toString()));
    }

    @GetMapping("/listar/{cod}/page")
    public ResponseEntity<Page<ReesDTO>> listarRegistros(@PathVariable String cod,
                                                         @RequestParam(defaultValue = "1") int page,
                                                         @RequestParam(defaultValue = "8") int size) {
        return ResponseEntity.ok(this.reesService.listarRegistros(cod, page, size));
    }

    @GetMapping("/listar/{cod}")
    public ResponseEntity<List<ReesDTO>> listarRegistros(@PathVariable String cod) {
        return ResponseEntity.ok(this.reesService.listarTodosRegistros(cod));
    }

    @GetMapping("/conteudo/{cod}")
    public ResponseEntity<List<String>> listarConteudos(@PathVariable String cod) {
        return ResponseEntity.ok(this.reesService.getConteudoByMateria(cod));
    }


    @DeleteMapping("/excluir/{cod}")
    public ResponseEntity<ResponseDTO> excluirEstudo(@PathVariable String cod) {
        this.reesService.excluirEstudo(cod);
        return ResponseEntity.ok().body(
                new ResponseDTO(
                        "Registro de estudo excluido com sucesso",
                        HttpStatus.OK.toString()));
    }

    @PutMapping("/editar")
    public ResponseEntity<ResponseDTO> editarEstudo(@RequestBody AtualizaReesDTO dto) {
        this.reesService.editarEstudo(dto);
        return ResponseEntity.ok().body(
                new ResponseDTO(
                        "Registro de estudo editado com sucesso",
                        HttpStatus.OK.toString()));
    }

    @PutMapping("/editar/conteudo")
    public ResponseEntity<ResponseDTO> editarConteudo(@RequestBody EditarConteudoDTO dto) {
        this.reesService.editarConteudo(dto);
        return ResponseEntity.ok().body(
                new ResponseDTO(
                        "Conteudo editado com sucesso",
                        HttpStatus.OK.toString()));
    }

    @GetMapping("/listar/{cod}/{data}/page")
    public ResponseEntity<Page<ReesDTO>> listarRegistroPorData(@PathVariable String cod, @PathVariable String data,
                                                @RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(this.reesService.listarRegistrosPorData(cod, data, page, size));
    }

}
