package org.dasein.cloud;

import javax.annotation.Nonnull;

/**
 * User: daniellemayne
 * Date: 15/12/2015
 * Time: 16:20
 */
public class ResourceNotFoundException extends CloudException {
    public ResourceNotFoundException(@Nonnull String resourceType, @Nonnull String resourceId) {
        super(resourceType+" with id "+resourceId+" can't be found");
        this.errorType = CloudErrorType.RESOURCE_NOT_FOUND;
    }
}
