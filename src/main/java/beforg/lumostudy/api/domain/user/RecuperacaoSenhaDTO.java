package beforg.lumostudy.api.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RecuperacaoSenhaDTO (
        @NotBlank(message = "O email não pode ser vazio.")
        @Email(message = "Email inválido.")
        String email) {
}
