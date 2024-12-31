package beforg.lumostudy.api.service;

import beforg.lumostudy.api.domain.materia.*;
import beforg.lumostudy.api.domain.user.Conta;
import beforg.lumostudy.api.infra.exception.ContaNotFoundException;
import beforg.lumostudy.api.infra.exception.MateriaNotFoundException;
import beforg.lumostudy.api.infra.exception.ResourceNotFoundException;
import beforg.lumostudy.api.repository.ContaRepository;
import beforg.lumostudy.api.repository.MateriaRepository;
import beforg.lumostudy.api.repository.ReesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {
    @Autowired
    private MateriaRepository materiaRepository;
    @Autowired
    private ReesRepository reesRepository;
    @Autowired
    private ContaRepository contaRepository;

    public void cadastrarMateria(CadastroMateriaDTO dto, String cod) {
        try {
            Conta conta = this.contaRepository.findByCod(cod);
            if (conta == null) {
                throw new ContaNotFoundException("Conta não encontrada");
            }
            Materia materia = new Materia(dto);
            materia.setConta(conta);
            this.materiaRepository.save(materia);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao cadastrar materia: " + e.getMessage());
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
        this.reesRepository.deleteAllByMateriaCod(cod);
        this.materiaRepository.deleteByCod(cod);
    }

    public void atualizarMateria(AtualizaMateriaDTO dto) {
        try {
            Materia materia = this.materiaRepository.findByCod(dto.cod());

            if (materia == null)  {
                throw new MateriaNotFoundException("Materia não encontrada");
            }

            materia.setNome(dto.nome());
            materia.setCategoria(dto.categoria());
            this.materiaRepository.save(materia);

        } catch (MateriaNotFoundException e) {
            throw new RuntimeException("Erro ao atualizar materia: " + e.getMessage());
        }

    }

    public List<String> categoriasMaterias(String cod) {
        return this.materiaRepository.findAllCategorias(cod);
    }
}
