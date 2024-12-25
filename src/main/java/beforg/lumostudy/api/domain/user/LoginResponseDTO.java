package beforg.lumostudy.api.domain.user;

public record LoginResponseDTO(String token, String cod, String email, String nome) {
}
