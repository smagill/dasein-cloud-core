package org.dasein.cloud;

import org.apache.http.HttpStatus;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

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

    /**
     * Constructs an unlabeled exception with default 401 Unauthorised fault type
     */
    public AuthenticationException() {
        super("Cloud authentication failed");
        this.errorType = CloudErrorType.AUTHENTICATION;
        this.authFaultType = AuthenticationFaultType.UNAUTHORISED;
    }

    /**
     * Constructs an authentication exception with a specific error message and default 401 Unauthorised fault type
     * @param msg the message for the error that occurred
     */
    public AuthenticationException(@Nonnull String msg) {
        super(msg);
        this.errorType = CloudErrorType.AUTHENTICATION;
        this.authFaultType = AuthenticationFaultType.UNAUTHORISED;
    }

    /**
     * Constructs an authentication exception in response to a specific cause with default 401 Unauthorised fault type
     * @param cause the error that caused this exception to be thrown
     */
    public AuthenticationException(@Nonnull Throwable cause) {
        super("Cloud authentication failed", cause);
        this.errorType = CloudErrorType.AUTHENTICATION;
        this.authFaultType = AuthenticationFaultType.UNAUTHORISED;
    }

    /**
     * Constructs an authentication exception in response to a specific fault type.
     * @param authFaultType the error that caused this exception to be thrown
     */
    public AuthenticationException(AuthenticationFaultType authFaultType) {
        super("Cloud authentication failed");
        this.errorType = CloudErrorType.AUTHENTICATION;
        this.authFaultType = authFaultType;
    }

    /**
     * Constructs an authentication exception with a specific error message in response to a specific fault type.
     * @param msg the message for the error that occurred
     * @param authFaultType the error that caused this exception to be thrown
     */
    public AuthenticationException(String msg, AuthenticationFaultType authFaultType) {
        super(msg);
        this.errorType = CloudErrorType.AUTHENTICATION;
        this.authFaultType = authFaultType;
    }

    /**
     * Constructs an authentication exception with a specific error message in response to a specific fault type and cause.
     * @param msg the message for the error that occurred
     * @param authFaultType the error that caused this exception to be thrown
     * @param cause the error that caused this exception to be thrown
     */
    public AuthenticationException(String msg, AuthenticationFaultType authFaultType, Throwable cause) {
        super(msg, cause);
        this.errorType = CloudErrorType.AUTHENTICATION;
        this.authFaultType = authFaultType;
    }

    /**
     * Constructs an authentication exception based on http code
     * authFaultType defaults to 401 Unauthorised
     * @param msg the message for the error that occurred
     * @param httpCode the HTTP error code
    */
    public AuthenticationException(String msg, @Nonnegative int httpCode) {
        super(msg);
        this.errorType = CloudErrorType.AUTHENTICATION;
        if (httpCode == HttpStatus.SC_FORBIDDEN) {
            this.authFaultType = AuthenticationFaultType.FORBIDDEN;
        }
        else {
            this.authFaultType = AuthenticationFaultType.UNAUTHORISED;
        }
    }

    /**
     * Constructs an authentication exception based on http code
     * authFaultType defaults to 401 Unauthorised
     * @param msg the error message
     * @param httpCode the HTTP error code
     * @param cause the error that caused this exception to be thrown
     */
    public AuthenticationException(String msg, @Nonnegative int httpCode, Throwable cause) {
        super(msg, cause);
        this.errorType = CloudErrorType.AUTHENTICATION;
        if (httpCode == HttpStatus.SC_FORBIDDEN) {
            this.authFaultType = AuthenticationFaultType.FORBIDDEN;
        }
        else {
            this.authFaultType = AuthenticationFaultType.UNAUTHORISED;
        }
    }

    public AuthenticationFaultType getAuthFaultType() {
        return authFaultType;
    }

}
