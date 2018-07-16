
package tk.yuqi.tools.tools;

import java.io.Serializable;

/**
 * 类 Result 的实现描述：Result
 *
 * @since 2018/7/15
 */
public class ResultDTO<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 3818711026618598571L;

    private T module;
    private boolean success = true;
    private String errorCode;
    private String errorMsg;

    public T getModule() {
        return module;
    }

    public void setModule(T module) {
        this.module = module;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean isSuccess) {
        this.success = isSuccess;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static <U> ResultDTO<U> assembleResult(boolean isSuccess, String errorCode, String errorMsg, U module) {
        ResultDTO<U> result = new ResultDTO<U>();
        result.setSuccess(isSuccess);
        result.setErrorCode(errorCode);
        result.setErrorMsg(errorMsg);
        result.setModule(module);
        return result;
    }

    public static <U> ResultDTO<U> trueResult(U module) {
        return assembleResult(true, null, null, module);
    }

    public static <U> ResultDTO<U> errorResult(String errorCode, String errorMsg) {
        return assembleResult(false, errorCode, errorMsg, null);
    }
}
