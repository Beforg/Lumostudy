package beforg.lumostudy.api.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "contas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Conta implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String cod;
    private String nome;
    @Column(name = "username")
    private String userNickName;
    @Email(message = "Email inválido")
    private String email;
    private String senha;
    private UserRole role;
    private String dataCriacao;
    private String activationCode;
    private String tokenRecuperacao;
    private boolean ativo;

    public Conta(String login, String senhaCriptografada, String nome, String userNickName) {
        this.email = login;
        this.senha = senhaCriptografada;
        this.role = UserRole.DEFAULT;
        this.nome = nome;
        this.ativo = false;
        this.userNickName = userNickName;
    }

    public void ativarConta() {
        this.setAtivo(true);
        this.dataCriacao = LocalDate.now().toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        } else if (this.role == UserRole.PREMIUM) {
            return List.of(new SimpleGrantedAuthority("ROLE_PREMIUM"), new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return  email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return ativo;
    }
}
