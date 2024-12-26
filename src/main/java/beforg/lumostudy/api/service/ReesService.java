package beforg.lumostudy.api.service;

import beforg.lumostudy.api.domain.materia.Materia;
import beforg.lumostudy.api.domain.materia.MateriaDTO;
import beforg.lumostudy.api.domain.registro.*;
import beforg.lumostudy.api.domain.user.Conta;
import beforg.lumostudy.api.infra.exception.ResourceNotFoundException;
import beforg.lumostudy.api.infra.security.SecurityUtil;
import beforg.lumostudy.api.repository.ContaRepository;
import beforg.lumostudy.api.repository.MateriaRepository;
import beforg.lumostudy.api.repository.ReesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReesService {
    @Autowired
    private ReesRepository reesRepository;
    @Autowired
    private MateriaRepository materiaRepository;
    @Autowired
    private ContaRepository contaRepository;

    public void registrarEstudo(CadastroReesDTO dto, String cod) {
        try {
            Materia materia = materiaRepository.findById(dto.codMateria())
                    .orElseThrow(() -> new ResourceNotFoundException("Nenhuma matéria encontrada."));
            Rees rees = new Rees(dto, materia);
            rees.setMateria(materia);
            materia.incrementarEstudo();
            materiaRepository.save(materia);
            Conta conta = this.contaRepository.findByCod(cod);
            rees.setConta(conta);
            this.reesRepository.save(rees);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao registrar estudo", e);
        }
    }

    public List<String> getConteudoByMateria(String cod) {
        return this.reesRepository.findByMateriaCod(cod);
    }

    public Page<ReesDTO> listarRegistrosPorData(String codConta, String data, int page, int size) {
        return this.reesRepository.findByData(codConta, data, PageRequest.of(page, size))
                .map(ReesDTO::new);
    }

    public Page<ReesDTO> listarRegistros(String cod, int page, int size) {
        Conta conta = this.contaRepository.findByCod(cod);
       return this.reesRepository.findByContaCod(conta.getCod(), PageRequest.of(page, size))
               .map(ReesDTO::new);
    }

    public void editarEstudo(AtualizaReesDTO dto) {
        Rees rees = this.reesRepository.findById(dto.codRegistro())
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum resultado para a busca."));
        rees.setConteudo(dto.conteudo());
        rees.setDescricao(dto.descricao());
        this.reesRepository.save(rees);

    }

    public void excluirEstudo(String cod) {
        this.reesRepository.deleteByCod(cod);
    }
}
