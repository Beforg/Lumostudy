package beforg.lumostudy.api.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String cod;
    private String nome;
    private String telefone;
    private String email;
    private String senha;
}
