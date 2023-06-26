package mg.marketmanagement.categoryservice.exception;

public enum ErrorCode {
    CATEGORY_NOT_VALID(2000),
    CATEGORY_NOT_FOUND(2100);

    private final int code;

    ErrorCode(int code){
        this.code= code;
    }

    public int getError() {
        return this.code;
    }
}
