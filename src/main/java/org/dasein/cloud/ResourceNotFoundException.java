package org.dasein.cloud;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * User: daniellemayne
 * Date: 15/12/2015
 * Time: 16:20
 */
public class ResourceNotFoundException extends CloudException {
    public ResourceNotFoundException(@Nonnull String msg) {
        super(msg);
        this.errorType = CloudErrorType.RESOURCE_NOT_FOUND;
    }

    public ResourceNotFoundException(@Nonnull String msg, @Nonnull Throwable cause) {
        super(msg, cause);
        this.errorType = CloudErrorType.RESOURCE_NOT_FOUND;
    }

    public ResourceNotFoundException(@Nonnegative int httpCode, @Nullable String providerCode, @Nonnull String msg) {
        super(msg);
        this.httpCode = httpCode;
        this.providerCode = providerCode;
    }

    public ResourceNotFoundException(@Nonnegative int httpCode, @Nullable String providerCode, @Nonnull String msg, @Nonnull Throwable cause) {
        super(msg, cause);
        this.httpCode = httpCode;
        this.providerCode = providerCode;
    }

    @Nonnull
    public CloudErrorType getErrorType() {
        return (errorType == null ? CloudErrorType.RESOURCE_NOT_FOUND : errorType);
    }
}
