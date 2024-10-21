package beforg.lumostudy.api.domain.materia;

import beforg.lumostudy.api.domain.user.Conta;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "materias")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String cod;
    private String nome;
    private String categoria;
    @ManyToOne
    @JoinColumn(name = "conta_cod")
    private Conta conta;

    public Materia(CadastroMateriaDTO dto) {
        this.nome = dto.nome();
        this.categoria = dto.categoria();
    }
}