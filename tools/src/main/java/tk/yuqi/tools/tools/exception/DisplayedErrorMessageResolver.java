package tk.yuqi.tools.tools.exception;

import java.util.Locale;

public interface DisplayedErrorMessageResolver {

    String resolve(String errorCode, String readableErrorCode, Locale locale, Object... params);

}
