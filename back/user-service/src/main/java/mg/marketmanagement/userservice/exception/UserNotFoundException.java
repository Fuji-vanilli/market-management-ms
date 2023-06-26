package mg.marketmanagement.userservice.exception;

public class UserNotFoundException extends RuntimeException{
    private ErrorCode errorCode;

    public UserNotFoundException(String message){
        super(message);
    }
    public UserNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
    public UserNotFoundException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode= errorCode;
    }
}
