package beforg.lumostudy.api.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UpdateContaDTO(
        @NotBlank String password,
        @NotBlank String type,
        @NotBlank String newValue) {
}
