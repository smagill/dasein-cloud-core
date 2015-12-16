package org.dasein.cloud;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * An error which has been caused by the resource being in an incompatible state for the operation
 * User: daniellemayne
 * Date: 27/11/2015
 * Time: 15:21
 */
public class InvalidStateException extends CloudException {
    public InvalidStateException(String msg) {
        super(msg);
        this.errorType = CloudErrorType.INVALID_STATE;
    }

    public InvalidStateException(@Nonnull String msg, @Nonnull Throwable cause) {
        super(msg, cause);
        this.errorType = CloudErrorType.INVALID_STATE;
    }

    public InvalidStateException(@Nonnull CloudErrorType type, @Nonnegative int httpCode, @Nullable String providerCode, @Nonnull String msg) {
        super(msg);
        this.errorType = type;
        this.httpCode = httpCode;
        this.providerCode = providerCode;
    }

    public InvalidStateException(@Nonnull CloudErrorType type, @Nonnegative int httpCode, @Nullable String providerCode, @Nonnull String msg, @Nonnull Throwable cause) {
        super(msg, cause);
        this.errorType = type;
        this.httpCode = httpCode;
        this.providerCode = providerCode;
    }

    @Nonnull
    public CloudErrorType getErrorType() {
        return (errorType == null ? CloudErrorType.GENERAL : errorType);
    }
}
