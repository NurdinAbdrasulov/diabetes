package kg.neobis.diabetes.exception;

import org.springframework.http.HttpStatus;

public class RecordNotFoundException extends BaseException{

    public RecordNotFoundException(String message){
        super(message, HttpStatus.NOT_FOUND);
    }

}
