package beforg.lumostudy.api.domain.user;

import jakarta.validation.constraints.NotBlank;

public record ResetarSenhaDTO(
        @NotBlank(message = "A senha não pode ser vazia.")
        String senha) {
}
