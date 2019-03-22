package tk.yuqi.tools.tools.exception;


public class TkYuException extends ErrorMessageException {
    public TkYuException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

    public TkYuException(ErrorMessage errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
