package kg.neobis.diabetes.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)//TO DO
    public ResponseStatusException handle(BaseException e){
        throw new ResponseStatusException(e.getStatus(), e.getMessage());
    }
}
