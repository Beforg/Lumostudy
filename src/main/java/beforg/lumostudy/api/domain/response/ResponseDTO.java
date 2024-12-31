package beforg.lumostudy.api.domain.response;

public record ResponseDTO(String message, String httpStatus) {
    public ResponseDTO(String message, String httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
