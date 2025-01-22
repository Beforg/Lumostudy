package beforg.lumostudy.api.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record RegistroDTO(
                          @NotBlank
                          @Email(message = "Email inválido.")
                          String email,
                          @NotBlank(message = "A senha não pode ser inválida.")
                          String senha,
                          @NotBlank(message = "O nome não pode ser inválido.")
                          String nome,
                          String userNickName) {
}
