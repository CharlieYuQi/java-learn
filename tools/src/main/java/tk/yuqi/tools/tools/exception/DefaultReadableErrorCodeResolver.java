package tk.yuqi.tools.tools.exception;


public class DefaultReadableErrorCodeResolver implements ReadableErrorCodeResolver {
    private static MessageProperties messageProperties = ResourceBundleMessageLoader.load("error_readable");

    private static final String DEFAULT_ERROR_CODE = "SYSTEM_ERROR";

    @Override
    public String resolve(String code) {
        String readable =  messageProperties.getMessage(code);
        return readable == null ? DEFAULT_ERROR_CODE : readable;
    }
}
