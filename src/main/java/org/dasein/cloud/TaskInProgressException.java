package org.dasein.cloud;

import javax.annotation.Nonnull;

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
}
