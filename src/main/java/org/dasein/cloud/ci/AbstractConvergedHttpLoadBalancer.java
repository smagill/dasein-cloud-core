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

package org.dasein.cloud.ci;

import java.util.ArrayList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.dasein.cloud.*;

public abstract class AbstractConvergedHttpLoadBalancer<T extends CloudProvider> implements ConvergedHttpLoadBalancerSupport {
    private T provider;

    public AbstractConvergedHttpLoadBalancer(@Nonnull T provider) {
        this.provider = provider;
    }

    protected final @Nonnull T getProvider() {
        return provider;
    }

    protected final @Nonnull ProviderContext getContext() throws InternalException {
        ProviderContext ctx = getProvider().getContext();

        if( ctx == null ) {
            throw new InternalException("No context was specified for this request");
        }
        return ctx;
    }

    @Override
    public @Nullable ConvergedHttpLoadBalancer getConvergedHttpLoadBalancer(@Nonnull String convergedHttpLoadBalancerName) throws CloudException, InternalException {
        throw new OperationNotSupportedException("getConvergedHttpLoadBalancer not supported for "+getProvider().getCloudName());
    }

    @Override
    public @Nonnull Iterable<ResourceStatus> listConvergedHttpLoadBalancerStatus() throws InternalException, CloudException {
        throw new OperationNotSupportedException("listConvergedHttpLoadBalancerStatus not supported for "+getProvider().getCloudName());
    }

    @Override
    public String createConvergedHttpLoadBalancer(@Nonnull ConvergedHttpLoadBalancer withConvergedHttpLoadBalancerOptions) throws CloudException, InternalException {
        throw new OperationNotSupportedException("createConvergedHttpLoadBalancer not supported for "+getProvider().getCloudName());
    }

    @Override
    public void removeConvergedHttpLoadBalancers(@Nonnull String globalForwardingRuleName) throws CloudException, InternalException {
        throw new OperationNotSupportedException("removeConvergedHttpLoadBalancers not supported for "+getProvider().getCloudName());
    }

}
