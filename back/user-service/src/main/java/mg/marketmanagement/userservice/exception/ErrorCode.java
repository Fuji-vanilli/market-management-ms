package mg.marketmanagement.userservice.exception;

public enum ErrorCode {
    USER_NOT_VALID(1000),
    USER_NOT_FOUND(1100);

    private final int code;

    ErrorCode(int code){
        this.code= code;
    }

    public int getError() {
        return this.code;
    }
}
