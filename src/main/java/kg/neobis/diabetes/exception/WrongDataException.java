package kg.neobis.diabetes.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class WrongDataException extends BaseException{

    public WrongDataException(String message){
        super(message, HttpStatus.BAD_REQUEST);
    }
    public WrongDataException(String message, HttpStatus status){
        super(message, status);
    }
}
