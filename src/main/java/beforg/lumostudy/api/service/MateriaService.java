package beforg.lumostudy.api.service;

import beforg.lumostudy.api.domain.materia.*;
import beforg.lumostudy.api.domain.user.Conta;
import beforg.lumostudy.api.infra.exception.ResourceNotFoundException;
import beforg.lumostudy.api.repository.ContaRepository;
import beforg.lumostudy.api.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService {
    @Autowired
    private MateriaRepository materiaRepository;
    @Autowired
    private ContaRepository contaRepository;

    public void cadastrarMateria(CadastroMateriaDTO dto, String cod) {
        try {
            Conta conta = this.contaRepository.findByCod(cod);
            if (conta == null) {
                throw new ResourceNotFoundException("CONTA not found for the authenticated user");
            }
            Materia materia = new Materia(dto);
            materia.setConta(conta);
            this.materiaRepository.save(materia);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao cadastrar matéria", e);
        }
    }
    public List<MateriaDTO> listarMaterias(String cod) {
        return this.materiaRepository.findByContaCod(cod)
                .stream()
                .map(MateriaDTO::new)
                .toList();
    }

    public Page<MateriaDTO> listarMateriasPorPagina(String cod, int page, int size) {
        return this.materiaRepository.findPageByContaCod(cod, PageRequest.of(page, size))
                .map(MateriaDTO::new);
    }

    public void excluirMateria(String cod) {
        this.materiaRepository.deleteByCod(cod);
    }

    public void atualizarMateria(AtualizaMateriaDTO dto) {
    Materia materia = this.materiaRepository.findById(dto.cod())
            .orElseThrow(() -> new ResourceNotFoundException("Nenhum resultado para a busca."));

    materia.setNome(dto.nome());
    materia.setCategoria(dto.categoria());

    this.materiaRepository.save(materia);
    }

    public List<String> categoriasMaterias(String cod) {
        return this.materiaRepository.findAllCategorias(cod);
    }
}
