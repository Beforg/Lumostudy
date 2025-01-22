package beforg.lumostudy.api.domain.response;

public record LoginResponseDTO(String token, String cod, String email, String nome, String username, double pontuacao, String dataCriacao) {
}
