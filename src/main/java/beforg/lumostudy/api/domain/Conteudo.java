package beforg.lumostudy.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "conteudos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Conteudo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String cod;
    private String nome;
    private String descricao;
    private String tipo;
    @ManyToOne
    private Materia materia;
    @ManyToOne
    private Cronograma cronograma;
    @ManyToOne
    private RegistroEstudo registroEstudo;
}
