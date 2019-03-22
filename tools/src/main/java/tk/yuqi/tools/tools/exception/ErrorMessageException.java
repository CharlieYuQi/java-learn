package tk.yuqi.tools.tools.exception;


public class ErrorMessageException extends RuntimeException {
    private ErrorMessage errorMessage;

    public ErrorMessageException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

    public ErrorMessageException(ErrorMessage errorMessage, Throwable cause) {
        super(errorMessage.getMessage(), cause);
        this.errorMessage = errorMessage;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}
