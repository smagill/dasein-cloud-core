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

    public CommunicationException(@Nonnegative int httpCode, @Nullable String providerCode, @Nonnull String msg) {
        super(msg);
        this.httpCode = httpCode;
        this.providerCode = providerCode;
    }

    public CommunicationException(@Nonnegative int httpCode, @Nullable String providerCode, @Nonnull String msg, @Nonnull Throwable cause) {
        super(msg, cause);
        this.httpCode = httpCode;
        this.providerCode = providerCode;
    }
}
