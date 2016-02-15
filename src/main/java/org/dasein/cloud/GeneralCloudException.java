/**
 * Copyright (C) 2009-2016 Dell, Inc.
 * See annotations for authorship information
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

package org.dasein.cloud;

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
    public GeneralCloudException(@Nonnull String msg) {
        super(msg);
        errorType = CloudErrorType.GENERAL;
    }
    
    public GeneralCloudException(@Nullable Throwable cause) {
        super(cause);
        this.errorType = CloudErrorType.GENERAL;
    }

    public GeneralCloudException(@Nonnull String msg, @Nullable Throwable cause) {
        super(msg, cause);
        this.errorType = CloudErrorType.GENERAL;
    }

}
