package beforg.lumostudy.api.domain.registro;

import beforg.lumostudy.api.domain.materia.Materia;
import beforg.lumostudy.api.domain.user.Conta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "registros_estudos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rees {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String cod;
    private String tempo;
    private String conteudo;
    private String descricao;
    private String data;
    @ManyToOne
    @JoinColumn(name = "materia_cod")
    private Materia materia;
    @ManyToOne
    @JoinColumn(name = "conta_cod")
    private Conta conta;
    private double pontuacao;

    public Rees(CadastroReesDTO dto, Materia materia) {
        this.tempo = dto.tempo();
        this.conteudo = dto.conteudo();
        this.descricao = dto.descricao();
        this.data = LocalDate.now().toString();
        this.materia = materia;
        this.pontuacao = Double.parseDouble(dto.pontuacao());
    }
}
