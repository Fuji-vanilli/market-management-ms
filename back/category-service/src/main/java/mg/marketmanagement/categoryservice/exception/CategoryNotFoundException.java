package mg.marketmanagement.categoryservice.exception;

public class CategoryNotFoundException extends RuntimeException{
    private ErrorCode errorCode;

    public CategoryNotFoundException(String message){
        super(message);
    }
    public CategoryNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
    public CategoryNotFoundException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode= errorCode;
    }
}
