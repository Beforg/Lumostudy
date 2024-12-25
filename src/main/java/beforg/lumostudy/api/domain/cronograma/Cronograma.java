package beforg.lumostudy.api.domain.cronograma;

import beforg.lumostudy.api.domain.materia.Materia;
import beforg.lumostudy.api.domain.user.Conta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cronograma")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cronograma {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String cod;
    private String data;
    private String conteudo;
    @ManyToOne
    @JoinColumn(name = "materia_cod" )
    private Materia materia;
    private boolean concluido;
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "conta_cod")
    private Conta conta;

    public Cronograma(CadastroCronogramaDTO dto) {
        this.data = dto.data();
        this.conteudo = dto.conteudo();
        this.descricao = dto.descricao();
        this.concluido = false;

    }
}
