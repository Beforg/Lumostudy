package beforg.lumostudy.api.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthDTO(
        @NotBlank (message = "O email não inserido.")
        @Email (message = "Email inválido.")
        String email,
        @NotBlank(message = "A senha não pode ser inválida.")
        String senha) {
}
