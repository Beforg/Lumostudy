package beforg.lumostudy.api.infra.exception;

public record ErrorResponseDTO(String message, String httpStatus) {
    public ErrorResponseDTO(String message, String httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
