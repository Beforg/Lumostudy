package beforg.lumostudy.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "registros_estudos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroEstudo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String cod;
    private String tempo;
    private String data;
    @ManyToOne
    private Conteudo conteudo;
}
