package EntityLayer.Ortak;
import java.io.Serializable;

import ToolLayer.OrbisDefaultException;

/**
 * Created by Ömer YILDIRIM  on 23.1.2016.
 */


public class Result<T extends Serializable> implements Serializable {

    private T		result;
    private String	error;
    private String	warning;

    public boolean isSucceded() {
        return error == null;
    }

    public T getResult() {
        return result;
    }

    public Result<T> setResult(final T result) {
        this.result = result;
        return this;
    }

    public String getError() {
        return error;
    }

    public Result<T> setError(final String error) {
        this.error = error;
        return this;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(final String warning) {
        this.warning = warning;
    }

    public Result<T> addError(final String error) {
        if (this.error == null || this.error.length() == 0)
            this.error = error;
        else
            this.error += "\n" + error;
        return this;
    }

    public String[] getErrors() {
        if (this.error == null)
            return null;
        else
            return this.error.split("\n");
    }

    public boolean hasWarning() {
        return warning != null;
    }

    public Result<T> addWarning(final String warning) {
        if (this.warning == null || this.warning.length() == 0)
            this.warning = warning;
        else
            this.warning += "\n" + warning;
        return this;
    }

    public String[] getWarnings() {
        if (this.warning == null)
            return null;
        else
            return this.warning.split("\n");
    }

    public Result<T> throwErrors() throws OrbisDefaultException {
        if (getError() != null)
            throw new OrbisDefaultException(getError());
        else
            return this;
    }

    public Result<T> setErrorCode(final long moduleId, final String errorCode, final Object... args) {
        return setError(moduleId + "é" + errorCode);
    }

    public Result<T> addErrorCode(final long moduleId, final String errorCode, final Object... args) {
        return addError(moduleId + "é" + errorCode);
    }

    public Result<T> assign(final Result<T> result) {
        this.result = result.result;
        this.error = result.error;
        this.warning = result.warning;
        return this;
    }

}
