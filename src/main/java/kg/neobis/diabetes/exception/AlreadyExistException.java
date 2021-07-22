package kg.neobis.diabetes.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistException extends BaseException {
    public AlreadyExistException(String message){
        this(message, HttpStatus.CONFLICT);
    }
    public AlreadyExistException(String message, HttpStatus status){
        super(message, status);
    }
}
