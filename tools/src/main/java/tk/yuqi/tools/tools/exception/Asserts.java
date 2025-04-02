package tk.yuqi.tools.tools.exception;

import java.util.Locale;

import org.jetbrains.annotations.PropertyKey;

public class Asserts {

    public static void isNull(Object v, @PropertyKey(resourceBundle = ErrorMessage.BUNDLE) String code, Locale locale,
                              Object... params) {
        if (null == v) {
            throwErrorMessageException(code, locale, params);
        }
    }

    public static void isNull(Object v, @PropertyKey(resourceBundle = ErrorMessage.BUNDLE) String code,
                              Object... params) {
        if (null == v) {
            throwErrorMessageException(code, null, params);
        }
    }

    public static void isBlank(String v, @PropertyKey(resourceBundle = ErrorMessage.BUNDLE) String code, Locale locale,
                              Object... params) {
        if (null == v) {
            throwErrorMessageException(code, locale, params);
        }
    }

    public static void isBlank(String v, @PropertyKey(resourceBundle = ErrorMessage.BUNDLE) String code,
                              Object... params) {
        if (null == v) {
            throwErrorMessageException(code, null, params);
        }
    }

    public static void equalsTrue(boolean v, @PropertyKey(resourceBundle = ErrorMessage.BUNDLE) String code,
                                  Locale locale, Object... params) {
        if (v) {
            throwErrorMessageException(code, locale, params);
        }
    }

    public static void equalsTrue(boolean v, @PropertyKey(resourceBundle = ErrorMessage.BUNDLE) String code,
                                  Object... params) throws ErrorMessageException {
        if (v) {
            throwErrorMessageException(code, null, params);
        }
    }

    public static void equalsFalse(boolean v, @PropertyKey(resourceBundle = ErrorMessage.BUNDLE) String code,
                                   Locale locale, Object... params) {
        if (!v) {
            throwErrorMessageException(code, locale, params);
        }
    }

    public static void equalsFalse(boolean v, @PropertyKey(resourceBundle = ErrorMessage.BUNDLE) String code,
                                   Object... params) {
        if (!v) {
            throwErrorMessageException(code, null, params);
        }
    }

    private static void throwErrorMessageException(String code, Locale locale, Object... params)
        throws ErrorMessageException {
        ErrorMessage errorMessage = ErrorMessage.of(code, locale, params);
        throw new ErrorMessageException(errorMessage);
    }

    public static void main(String[] args) {
        try {
            Asserts.isNull(null, "F-1",Locale.SIMPLIFIED_CHINESE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
