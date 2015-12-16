package org.dasein.cloud;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * An error returned from the cloud - could be cloud specific or not important enough
 * to warrant its own exception
 * User: daniellemayne
 * Date: 27/11/2015
 * Time: 15:21
 */
public class GeneralCloudException extends CloudException {
    public GeneralCloudException(@Nonnull String msg, @Nonnull CloudErrorType errorType) {
        super(msg);
        this.errorType = errorType;
    }

    public GeneralCloudException(@Nonnull String msg, @Nonnull Throwable cause, @Nonnull CloudErrorType errorType) {
        super(msg, cause);
        this.errorType = errorType;
    }

    public GeneralCloudException(@Nonnull CloudErrorType type, @Nonnegative int httpCode, @Nullable String providerCode, @Nonnull String msg) {
        super(msg);
        this.errorType = type;
        this.httpCode = httpCode;
        this.providerCode = providerCode;
    }

    public GeneralCloudException(@Nonnull CloudErrorType type, @Nonnegative int httpCode, @Nullable String providerCode, @Nonnull String msg, @Nonnull Throwable cause) {
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
