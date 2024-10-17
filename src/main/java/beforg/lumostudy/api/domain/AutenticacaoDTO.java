package beforg.lumostudy.api.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AutenticacaoDTO(
        @NotBlank (message = "O email não inserido.")
        @Email (message = "Email inválido.")
        String email,
        @NotBlank(message = "A senha não pode ser inválida.")
        String senha) {
}
