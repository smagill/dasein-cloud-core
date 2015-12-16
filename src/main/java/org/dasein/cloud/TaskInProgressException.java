package org.dasein.cloud;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * User: daniellemayne
 * Date: 16/12/2015
 * Time: 12:21
 */
public class TaskInProgressException extends CloudException {
    public TaskInProgressException(@Nonnull String msg) {
        super(msg);
        this.errorType = CloudErrorType.TASK_IN_PROGRESS;
    }

    public TaskInProgressException(@Nonnull String msg, @Nonnull Throwable cause) {
        super(msg, cause);
        this.errorType = CloudErrorType.TASK_IN_PROGRESS;
    }

    public TaskInProgressException(@Nonnull CloudErrorType type, @Nonnegative int httpCode, @Nullable String providerCode, @Nonnull String msg) {
        super(msg);
        this.errorType = type;
        this.httpCode = httpCode;
        this.providerCode = providerCode;
    }

    public TaskInProgressException(@Nonnull CloudErrorType type, @Nonnegative int httpCode, @Nullable String providerCode, @Nonnull String msg, @Nonnull Throwable cause) {
        super(msg, cause);
        this.errorType = type;
        this.httpCode = httpCode;
        this.providerCode = providerCode;
    }

    @Nonnull
    public CloudErrorType getErrorType() {
        return (errorType == null ? CloudErrorType.TASK_IN_PROGRESS : errorType);
    }
}
