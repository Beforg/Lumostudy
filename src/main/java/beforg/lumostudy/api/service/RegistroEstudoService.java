package beforg.lumostudy.api.service;

import beforg.lumostudy.api.domain.materia.Materia;
import beforg.lumostudy.api.domain.registro.*;
import beforg.lumostudy.api.domain.user.Conta;
import beforg.lumostudy.api.infra.exception.ResourceNotFoundException;
import beforg.lumostudy.api.infra.security.SecurityUtil;
import beforg.lumostudy.api.repository.MateriaRepository;
import beforg.lumostudy.api.repository.RegistroEstudoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroEstudoService {
    @Autowired
    private RegistroEstudoRepository registroEstudoRepository;
    @Autowired
    private MateriaRepository materiaRepository;

    public void registrarEstudo(CadastroRegistroEstudoDTO dto) {
        try {
            Materia materia = materiaRepository.findById(dto.codMateria())
                    .orElseThrow(() -> new ResourceNotFoundException("Materia não encontrada"));
            RegistroEstudo registroEstudo = new RegistroEstudo(dto, materia);
            registroEstudo.setMateria(materia);
            registroEstudo.setConta(SecurityUtil.getAuthenticatedUser());
            this.registroEstudoRepository.save(registroEstudo);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao registrar estudo", e);
        }
    }

    public List<RegistroEstudoDTO> listarRegistros() {
        Conta conta = SecurityUtil.getAuthenticatedUser();
       return this. registroEstudoRepository.findByContaCod(conta.getCod()).stream()
               .map(RegistroEstudoDTO::new)
               .toList();

    }

    public void editarEstudo(AtualizaRegistroEstudoDTO dto) {
        RegistroEstudo registroEstudo = this.registroEstudoRepository.findById(dto.codRegistro())
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum resultado para a busca."));
        registroEstudo.setConteudo(dto.conteudo());
        registroEstudo.setDescricao(dto.descricao());
        this.registroEstudoRepository.save(registroEstudo);

    }

    public void excluirEstudo(ExcluiRegistroEstudoDTO dto) {
        this.registroEstudoRepository.deleteByCod(dto.codRegistro());

    }
}
