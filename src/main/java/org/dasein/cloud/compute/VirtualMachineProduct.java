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

package org.dasein.cloud.compute;

import org.dasein.cloud.InternalException;
import org.dasein.cloud.ProviderContext;
import org.dasein.cloud.VisibleScope;
import org.dasein.cloud.util.products.ProductReader;
import org.dasein.cloud.util.products.ProductReaderException;
import org.dasein.cloud.util.products.model.VmProductProvider;
import org.dasein.cloud.util.products.model.VolumeProvider;
import org.dasein.util.uom.storage.Gigabyte;
import org.dasein.util.uom.storage.Megabyte;
import org.dasein.util.uom.storage.Storage;
import org.dasein.util.uom.storage.StorageUnit;
import org.json.JSONArray;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@SuppressWarnings("UnusedDeclaration")
public class VirtualMachineProduct implements Serializable {
    private static final long serialVersionUID = -6761551014614219494L;

    private int                 cpuCount;
    private String              description;
    private Storage<Gigabyte>   rootVolumeSize;
    private String              name;
    private String              providerProductId;
    private Storage<Megabyte>   ramSize;
    private float               standardHourlyRate;
    private VisibleScope        visibleScope;
    private String              dataCenterId;
    private Architecture[]      architectures;

    private Map<String, String> providerMetadata;

    public enum Status {CURRENT, DEPRECATED;}

    private Status status = Status.CURRENT;

    public VirtualMachineProduct() {
    }

    static public @Nonnull VirtualMachineProduct[] fromConfigurationFile(@Nonnull String pathToFile, @Nonnull ProviderContext context) throws InternalException {
        try {
            VmProductProvider provider = ProductReader.readVmProducts(pathToFile, context.getCloud().getProviderName());
            List<VirtualMachineProduct> result = new ArrayList<>();
            List<org.dasein.cloud.util.products.model.VmProduct> products = provider.getProducts();
            for( org.dasein.cloud.util.products.model.VmProduct p : products ) {
                if( p.getExcludesRegions() != null && Arrays.binarySearch(p.getExcludesRegions(), context.getRegionId()) >= 0 ) {
                    continue;
                }
                VirtualMachineProduct product = new VirtualMachineProduct();
                product.setName(p.getName());
                product.setDescription(p.getDescription());
                product.setCpuCount(p.getCpuCount());
                product.setProviderProductId(p.getId());
                if( p.getRamSizeInMb() > 0) {
                    product.setRamSize(new Storage<>(p.getRamSizeInMb(), Storage.MEGABYTE));
                } else {
                    product.setRamSize(new Storage<>(512, Storage.MEGABYTE));
                }
                if( !p.isCurrent() ) {
                    product.setStatusDeprecated();
                }
                List<Architecture> architectures = new ArrayList<>();
                for( String arch : p.getArchitectures() ) {
                    architectures.add(Architecture.valueOf(arch));
                }
                product.setArchitectures(architectures.toArray(new Architecture[architectures.size()]));
                Map<String, Object> props = p.getCustomProperties();
                if( props != null ) {
                    for( String key : props.keySet() ) {
                        Object values = props.get(key);
                        if( values instanceof List ) {
                            List<String> arrayValues = ( List<String> ) values;
                            StringBuilder sb = new StringBuilder();

                            for( int i=0; i<arrayValues.size(); i++ ) {
                                if( sb.length() > 0 ) {
                                    sb.append(",");
                                }
                                sb.append(arrayValues.get(i));
                            }
                            product.getProviderMetadata().put(key, sb.toString());
                        }
                        else {
                            product.getProviderMetadata().put(key, ( String ) values);
                        }
                    }
                }
                result.add(product);
            }
            return result.toArray(new VirtualMachineProduct[result.size()]);
        }
        catch( ProductReaderException e ) {
            throw new InternalException("Unable to read product configuration file " + pathToFile, e);
        }
    }
    // TODO(stas): shouldn't this also compare other attributes?
    public boolean equals(Object ob) {
        return ( ob != null && ( ob == this || getClass().getName().equals(ob.getClass().getName()) && getProviderProductId().equals(( ( VirtualMachineProduct ) ob ).getProviderProductId()) ) );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(int cpuCount) {
        this.cpuCount = cpuCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProviderProductId() {
        return providerProductId;
    }

    public void setProviderProductId(@Nonnull String providerProductId) {
        this.providerProductId = providerProductId;
    }

    public Storage<Gigabyte> getRootVolumeSize() {
        return rootVolumeSize;
    }

    public void setRootVolumeSize(Storage<?> rootVolumeSize) {
        this.rootVolumeSize = ( Storage<Gigabyte> ) rootVolumeSize.convertTo(Storage.GIGABYTE);
    }

    public Storage<Megabyte> getRamSize() {
        return ramSize;
    }

    public void setRamSize(Storage<?> ramSize) {
        this.ramSize = ( Storage<Megabyte> ) ramSize.convertTo(Storage.MEGABYTE);
    }

    public float getStandardHourlyRate() {
        return standardHourlyRate;
    }

    public void setStandardHourlyRate(float standardHourlyRate) {
        this.standardHourlyRate = standardHourlyRate;
    }

    public void setVisibleScope(VisibleScope visibleScope) {
        this.visibleScope = visibleScope;
    }

    public VisibleScope getVisibleScope() {
        return this.visibleScope;
    }

    public String getDataCenterId() {
        return this.dataCenterId;
    }

    public void setDataCenterId(String dataCenterId){
        this.dataCenterId = dataCenterId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatusDeprecated() {
        this.status = Status.DEPRECATED;
    }

    public void setArchitectures(Architecture ... architectures) {
        this.architectures = architectures;
    }

    /**
     * List of supported architectures. This is of type List as opposed to Iterable as this is a model class,
     * so all data is already pre-populated.
     * @return list of architectures supported by this product
     */
    public @Nonnull Architecture[] getArchitectures() {
        return architectures != null ? architectures : new Architecture[0];
    }

    /**
     * Cloud-specific metadata that drivers may or may not use for matching products with machine images. This is considered driver-internal.
     * @return product metadata
     */
    public @Nonnull Map<String, String> getProviderMetadata() {
        if( providerMetadata == null ) {
            providerMetadata = new HashMap<String, String>();
        }
        return providerMetadata;
    }

    public void setProviderMetadata(@Nonnull Map<String, String> providerMetadata) {
        getProviderMetadata().putAll(providerMetadata);
    }

    public String toString() {
        return (name + " [" + providerProductId + "]");
    }
}
