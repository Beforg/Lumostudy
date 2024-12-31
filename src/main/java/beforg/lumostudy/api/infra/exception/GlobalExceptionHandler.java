package beforg.lumostudy.api.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.toString()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailExistenteException.class)
    public ResponseEntity<ErrorResponseDTO> handleEmailExistenteException(EmailExistenteException ex) {
        return new ResponseEntity<>(
                new ErrorResponseDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidPasswordException(InvalidPasswordException ex) {
        return new ResponseEntity<>(
                new ErrorResponseDTO(ex.getMessage(), HttpStatus.UNAUTHORIZED.toString()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex) {
        return new ResponseEntity<>(
                new ErrorResponseDTO(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.toString()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ContaNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleContaNotFoundException(ContaNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.toString()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MateriaNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleMateriaNotFoundException(MateriaNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.toString()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CronogramaNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleCronogramaNotFoundException(CronogramaNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.toString()), HttpStatus.NOT_FOUND);
    }
}