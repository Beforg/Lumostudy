package beforg.lumostudy.api.infra.exception;

public class MateriaNotFoundException extends RuntimeException {
    public MateriaNotFoundException(String message) {
        super(message);
    }
}
