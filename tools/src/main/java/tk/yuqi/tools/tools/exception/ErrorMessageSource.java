package tk.yuqi.tools.tools.exception;


import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.ServiceLoader;


public class ErrorMessageSource {
    private static ReadableErrorCodeResolver readableErrorCodeResolver = loadResolver(ReadableErrorCodeResolver.class);
    private static DisplayedErrorMessageResolver displayedErrorMessageResolver = loadResolver(DisplayedErrorMessageResolver.class);

    private static MessageProperties messageProperties = ResourceBundleMessageLoader.load("errors_en");

    private static <T> T loadResolver(Class<T> clazz) {
        Iterator<T> iterator = ServiceLoader.load(clazz).iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }

    public static ErrorMessage getErrorMessage(String code, String readableCode, Locale displayedLocale, Object... params) {
        ErrorMessage errorMessage = initErrorMessage(code);

        if (null != params && params.length > 0) {
            formatMessage(errorMessage, params);
        }

        if (null != readableCode) {
            errorMessage.readableCode = readableCode;
        } else {
            errorMessage.readableCode = resolveReadableErrorCode(code);
        }

        errorMessage.displayedMessage = resolveDisplayedMessage(code, errorMessage.readableCode, displayedLocale, params);

        return errorMessage;
    }

    private static ErrorMessage initErrorMessage(String code) {
        String message = messageProperties.getMessage(code);
        return new ErrorMessage(code, message);
    }

    private static void formatMessage(ErrorMessage errorMessage, Object...params) {
        String message = errorMessage.getMessage();
        if (null != message) {
            errorMessage.message = MessageFormat.format(message, params);
        }
    }

    private static String resolveReadableErrorCode(String code) {
        if (null != readableErrorCodeResolver) {
            return readableErrorCodeResolver.resolve(code);
        }

        return null;
    }

    private static String resolveDisplayedMessage(String code, String readableCode, Locale locale, Object... params) {
        if (null != displayedErrorMessageResolver) {
            return displayedErrorMessageResolver.resolve(code, readableCode, locale, params);
        }

        return null;
    }
}
