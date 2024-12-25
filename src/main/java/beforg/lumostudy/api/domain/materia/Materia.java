package beforg.lumostudy.api.domain.materia;

import beforg.lumostudy.api.domain.registro.Rees;
import beforg.lumostudy.api.domain.user.Conta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    @Column(name = "estudos_registrados")
    int estudosRegistrados;
    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rees> rees;

    public Materia(CadastroMateriaDTO dto) {
        this.nome = dto.nome();
        this.categoria = dto.categoria();
    }

    public void  incrementarEstudo() {
        this.estudosRegistrados++;
    }
}