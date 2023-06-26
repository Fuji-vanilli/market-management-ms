package mg.marketmanagement.userservice.exception;

import java.util.List;

public class UserNotValidException extends RuntimeException{
    private ErrorCode errorCode;
    private List<String> errors;

    public UserNotValidException(String message){
        super(message);
    }
    public UserNotValidException(String message, Throwable cause){
        super(message, cause);
    }
    public UserNotValidException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode= errorCode;
    }
    public UserNotValidException(String message, ErrorCode errorCode, List<String> errors){
        super(message);
        this.errorCode= errorCode;
        this.errors= errors;
    }
}
