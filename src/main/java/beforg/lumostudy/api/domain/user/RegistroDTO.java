package beforg.lumostudy.api.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegistroDTO(
                          @NotBlank
                          @Email(message = "Email inválido.")
                          String email,
                          @NotBlank(message = "A senha não pode ser inválida.")
                          String senha,
                          @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone deve estar no formato (XX) XXXXX-XXXX")
                          String telefone,
                          @NotBlank(message = "O nome não pode ser inválido.")
                          String nome) {
}
