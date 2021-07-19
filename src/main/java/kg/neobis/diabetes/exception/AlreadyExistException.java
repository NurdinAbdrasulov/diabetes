package kg.neobis.diabetes.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistException extends BaseException {
    public AlreadyExistException(String message){
        super(message, HttpStatus.CONFLICT);
    }
}
