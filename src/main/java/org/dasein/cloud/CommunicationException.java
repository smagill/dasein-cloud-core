package org.dasein.cloud;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * User: daniellemayne
 * Date: 30/11/2015
 * Time: 12:22
 */
public class CommunicationException extends CloudException {
    public CommunicationException(@Nonnull String msg) {
        super(msg);
        this.errorType = CloudErrorType.COMMUNICATION;
    }

    public CommunicationException(@Nonnull String msg, @Nonnull Throwable cause) {
        super(msg, cause);
        this.errorType = CloudErrorType.COMMUNICATION;
    }

    public CommunicationException(@Nonnull CloudErrorType type, @Nonnegative int httpCode, @Nullable String providerCode, @Nonnull String msg) {
        super(msg);
        this.errorType = type;
        this.httpCode = httpCode;
        this.providerCode = providerCode;
    }

    public CommunicationException(@Nonnull CloudErrorType type, @Nonnegative int httpCode, @Nullable String providerCode, @Nonnull String msg, @Nonnull Throwable cause) {
        super(msg, cause);
        this.errorType = type;
        this.httpCode = httpCode;
        this.providerCode = providerCode;
    }

    @Nonnull
    public CloudErrorType getErrorType() {
            return (errorType == null ? CloudErrorType.COMMUNICATION : errorType);
        }
}
