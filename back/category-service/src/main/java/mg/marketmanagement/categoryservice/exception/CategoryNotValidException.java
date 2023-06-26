package mg.marketmanagement.categoryservice.exception;

import java.util.List;

public class CategoryNotValidException extends RuntimeException{
    private ErrorCode errorCode;
    private List<String> errors;

    public CategoryNotValidException(String message){
        super(message);
    }
    public CategoryNotValidException(String message, Throwable cause){
        super(message, cause);
    }
    public CategoryNotValidException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode= errorCode;
    }
    public CategoryNotValidException(String message, ErrorCode errorCode, List<String> errors){
        super(message);
        this.errorCode= errorCode;
        this.errors= errors;
    }
}
