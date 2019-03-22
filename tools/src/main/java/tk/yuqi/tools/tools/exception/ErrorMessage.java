package tk.yuqi.tools.tools.exception;

import java.util.Locale;

import org.jetbrains.annotations.PropertyKey;


public class ErrorMessage {
    public static final String BUNDLE = "i18n.errors";
    private static Locale displayedLocale = Locale.SIMPLIFIED_CHINESE;

    private String code;

    String readableCode;

    String displayedMessage;

    String message;

    public ErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getReadableCode() {
        return readableCode;
    }

    public String getDisplayedMessage() {
        return displayedMessage;
    }

    public static ErrorMessage of(@PropertyKey(resourceBundle = BUNDLE) String code, Object... params) {
        return ErrorMessageSource.getErrorMessage(code, null, displayedLocale, params);
    }

    public static ErrorMessage of(@PropertyKey(resourceBundle = BUNDLE) String code, Locale locale, Object... params) {
        return ErrorMessageSource.getErrorMessage(code, null, locale, params);
    }

    public static ErrorMessage get(@PropertyKey(resourceBundle = BUNDLE) String code, String readableCode, Object... params) {
        return ErrorMessageSource.getErrorMessage(code, readableCode, displayedLocale, params);
    }

    public static ErrorMessage get(@PropertyKey(resourceBundle = BUNDLE) String code, String readableCode, Locale locale, Object... params) {
        return ErrorMessageSource.getErrorMessage(code, readableCode, locale, params);
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "code='" + code + '\'' +
                ", readableCode='" + readableCode + '\'' +
                ", displayedMessage='" + displayedMessage + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
