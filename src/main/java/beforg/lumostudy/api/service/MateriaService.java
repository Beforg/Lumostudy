package beforg.lumostudy.api.service;

import beforg.lumostudy.api.domain.materia.*;
import beforg.lumostudy.api.domain.user.Conta;
import beforg.lumostudy.api.infra.exception.ResourceNotFoundException;
import beforg.lumostudy.api.infra.security.SecurityUtil;
import beforg.lumostudy.api.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService {
    @Autowired
    private MateriaRepository materiaRepository;

    public void cadastrarMateria(CadastroMateriaDTO dto) {
        try {
            Materia materia = new Materia(dto);
            Conta conta = SecurityUtil.getAuthenticatedUser();
            if (conta == null) {
                throw new ResourceNotFoundException("CONTA not found for the authenticated user");
            }
            materia.setConta(conta);
            this.materiaRepository.save(materia);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao cadastrar matéria", e);
        }
    }
    public List<MateriaDTO> listarMaterias() {
        Conta conta = SecurityUtil.getAuthenticatedUser();
        return this.materiaRepository.findByContaCod(conta.getCod())
                .stream()
                .map(MateriaDTO::new)
                .toList();
    }
    public void excluirMateria(ExcluiMateriaDTO dto) {
        this.materiaRepository.deleteByCod(dto.cod());
    }

    public void atualizarMateria(AtualizaMateriaDTO dto) {
    Materia materia = this.materiaRepository.findById(dto.cod())
            .orElseThrow(() -> new ResourceNotFoundException("Nenhum resultado para a busca."));

    materia.setNome(dto.nome());
    materia.setCategoria(dto.categoria());

    this.materiaRepository.save(materia);
    }
}
