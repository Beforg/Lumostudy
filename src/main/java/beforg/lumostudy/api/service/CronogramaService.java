package beforg.lumostudy.api.service;

import beforg.lumostudy.api.domain.cronograma.CadastroCronogramaDTO;
import beforg.lumostudy.api.domain.cronograma.ConcluidoCronogramaDTO;
import beforg.lumostudy.api.domain.cronograma.Cronograma;
import beforg.lumostudy.api.domain.cronograma.CronogramaDTO;
import beforg.lumostudy.api.domain.materia.Materia;
import beforg.lumostudy.api.domain.registro.ReesDTO;
import beforg.lumostudy.api.domain.user.Conta;
import beforg.lumostudy.api.infra.exception.ContaNotFoundException;
import beforg.lumostudy.api.infra.exception.CronogramaNotFoundException;
import beforg.lumostudy.api.infra.exception.MateriaNotFoundException;
import beforg.lumostudy.api.repository.ContaRepository;
import beforg.lumostudy.api.repository.CronogramaRepository;
import beforg.lumostudy.api.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CronogramaService {
    @Autowired
    private CronogramaRepository repository;
    @Autowired
    private MateriaRepository materiaRepository;
    @Autowired
    private ContaRepository contaRepository;

    public void cadastrar(CadastroCronogramaDTO dto, String cod) {
            Cronograma cronograma = new Cronograma(dto);
            Materia materia = materiaRepository.findByCod(dto.materiaCod());

            if (materia == null) {
                throw new MateriaNotFoundException("Materia não encontrada");
            }

            cronograma.setMateria(materia);
            Conta conta = contaRepository.findByCod(cod);

            if (conta == null) {
                throw new ContaNotFoundException("Conta não encontrada");
            }
            cronograma.setConta(conta);
            repository.save(cronograma);

    }

    public List<CronogramaDTO> listar(String cod) {
        return repository.findByContaCod(cod).stream().
                map(CronogramaDTO::new)
                .collect(Collectors.toList());
    }

    public void editar(CronogramaDTO dto) {
        Cronograma cronograma = repository.findByCod(dto.cod());
        if ( cronograma == null) {
            throw new CronogramaNotFoundException("Item do cronograma não encontrado");
        }
        cronograma.setDescricao(dto.descricao());
        cronograma.setData(dto.data());
        cronograma.setConteudo(dto.conteudo());
        cronograma.setConcluido(dto.concluido());
        Materia materia = materiaRepository.findByCod(dto.materiaCod());

        if (materia == null) {
            throw new MateriaNotFoundException("Materia não encontrada");
        }

        cronograma.setMateria(materiaRepository.findByCod(dto.materiaCod()));
        repository.save(cronograma);
    }

    public void concluir(ConcluidoCronogramaDTO dto) {
        Cronograma cronograma = repository.findByCod(dto.cod());
        if (cronograma == null) {
            throw new CronogramaNotFoundException("Item do cronograma não encontrado");
        }
        cronograma.setConcluido(dto.concluido());
        repository.save(cronograma);
    }

    public void excluir(String cod) {
        repository.delete(repository.findByCod(cod));
    }

    public Page<CronogramaDTO> listarRegistrosHoje(String cod, int page, int size) {
        return this.repository.findByData(cod, LocalDate.now().toString(),PageRequest.of(page, size))
                .map(CronogramaDTO::new);
    }

    public Page<CronogramaDTO> listarRegistrosPorData(String cod, String data, int page, int size) {
        return this.repository.findByData(cod, data, PageRequest.of(page, size))
                .map(CronogramaDTO::new);
    }


    public Page<CronogramaDTO> listarRegistrosAtrasados(String cod, int page, int size) {
        return this.repository.findByDataBefore(cod, LocalDate.now().toString(),PageRequest.of(page, size))
                .map(CronogramaDTO::new);
    }

}
