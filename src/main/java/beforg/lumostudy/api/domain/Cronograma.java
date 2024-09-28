package beforg.lumostudy.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "cronogramas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cronograma {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String cod;
    private Date data;
    private boolean concluido;
    @ManyToOne
    private Conteudo conteudo;
}
