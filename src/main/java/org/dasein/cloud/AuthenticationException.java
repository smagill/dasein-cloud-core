package org.dasein.cloud;

import org.apache.http.HttpStatus;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * User: daniellemayne
 * Date: 25/11/2015
 * Time: 12:10
 */
public class AuthenticationException extends CloudException {
    public enum AuthenticationFaultType {
        UNAUTHORISED, FORBIDDEN;  //HTTP Status codes 401 and 403
    }

    private AuthenticationFaultType authFaultType;

    public AuthenticationException(@Nonnull String msg) {
        super(msg);
        this.errorType = CloudErrorType.AUTHENTICATION;
        this.authFaultType = AuthenticationFaultType.UNAUTHORISED;
    }

    public AuthenticationException(@Nonnull String msg, @Nonnull Throwable cause) {
        super(msg, cause);
        this.errorType = CloudErrorType.AUTHENTICATION;
        this.authFaultType = AuthenticationFaultType.UNAUTHORISED;
    }

    /**
     * Constructs a cloud exception with cloud provider data added in
     * @param type cloud error type
     * @param httpCode the HTTP error code
     * @param providerCode the provider-specific error code
     * @param msg the error message
     */
    public AuthenticationException(@Nonnull CloudErrorType type, @Nonnegative int httpCode, @Nullable String providerCode, @Nonnull String msg) {
        super(msg);
        this.errorType = type;
        this.httpCode = httpCode;
        this.providerCode = providerCode;
        if (httpCode == HttpStatus.SC_FORBIDDEN) {
            this.authFaultType = AuthenticationFaultType.FORBIDDEN;
        }
        else {
            this.authFaultType = AuthenticationFaultType.UNAUTHORISED;
        }
    }

    /**
     * Constructs a cloud exception with cloud provider data added in
     * @param type cloud error type
     * @param httpCode the HTTP error code
     * @param providerCode the provider-specific error code
     * @param msg the error message
     * @param cause the error that caused this exception to be thrown
     */
    public AuthenticationException(@Nonnull CloudErrorType type, @Nonnegative int httpCode, @Nullable String providerCode, @Nonnull String msg, @Nonnull Throwable cause) {
        super(msg, cause);
        this.errorType = type;
        this.httpCode = httpCode;
        this.providerCode = providerCode;
        if (httpCode == HttpStatus.SC_FORBIDDEN) {
            this.authFaultType = AuthenticationFaultType.FORBIDDEN;
        }
        else {
            this.authFaultType = AuthenticationFaultType.UNAUTHORISED;
        }
    }

    /**
     * Indicates a specific fault type for this exception.
     * @param authFaultType the error that caused this exception to be thrown
     * @return this
     */
    public @Nonnull AuthenticationException withFaultType(@Nonnull AuthenticationFaultType authFaultType) {
        this.authFaultType = authFaultType;
        return this;
    }

    public AuthenticationFaultType getAuthFaultType() {
        return authFaultType;
    }

    public @Nonnull CloudErrorType getErrorType() {
        return (errorType == null ? CloudErrorType.AUTHENTICATION : errorType);
    }
}
