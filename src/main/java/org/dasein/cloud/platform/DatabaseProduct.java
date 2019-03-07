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

package org.dasein.cloud.platform;

import org.dasein.cloud.InternalException;
import org.dasein.cloud.ProviderContext;
import org.dasein.cloud.util.products.ProductReader;
import org.dasein.cloud.util.products.ProductReaderException;
import org.dasein.cloud.util.products.model.DatabaseProductDefinition;
import org.dasein.cloud.util.products.model.DatabaseProductProvider;
import org.dasein.cloud.util.products.model.DatabaseRegion;
import org.dasein.cloud.util.products.model.VmProductProvider;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.dasein.cloud.platform.DatabaseLicenseModel.*;

public class DatabaseProduct implements Serializable {
    private static final long serialVersionUID = -5535140342676937943L;

    private String               currency;
    private DatabaseEngine       engine;
    private DatabaseLicenseModel licenseModel;
    private boolean              highAvailability;
    private String               name;
    private String               productSize;
    private String               providerDataCenterId;
    private float                standardHourlyRate;
    private float                standardIoRate;
    private float                standardStorageRate;
    private int                  storageInGigabytes;

    public DatabaseProduct( String databaseSizeId ) {
        this(databaseSizeId, databaseSizeId);
    }

    public DatabaseProduct( String databaseSizeId, String databaseSizeName ) {
        this.productSize = databaseSizeId;
        this.name = databaseSizeName;
    }

    static public @Nonnull DatabaseProduct[] fromConfigurationFile(@Nonnull String pathToFile, @Nonnull ProviderContext context) throws InternalException {
        try {
            List<DatabaseProduct> products = new ArrayList<>();
            DatabaseProductProvider databaseProvider = ProductReader.readDatabaseProducts(pathToFile,
                    context.getCloud().getProviderName());

            List<org.dasein.cloud.util.products.model.DatabaseEngine> databaseEngines = databaseProvider.getEngines();

            for( org.dasein.cloud.util.products.model.DatabaseEngine databaseEngine : databaseEngines ) {
                for ( DatabaseRegion region : databaseEngine.getRegions() ) {
                    if( region.getName().equalsIgnoreCase( context.getRegionId()) ) {
                        for( org.dasein.cloud.util.products.model.DatabaseProduct databaseProduct : region.getProducts() ) {
                            DatabaseProduct product = new DatabaseProduct(databaseProduct.getName());
                            product.setEngine(DatabaseEngine.valueOf(databaseEngine.getName()));
                            product.setHighAvailability(databaseProduct.isHighAvailability());
                            product.setStandardHourlyRate(databaseProduct.getHourlyRate());
                            product.setStandardIoRate(databaseProduct.getIoRate());
                            product.setStandardStorageRate(databaseProduct.getStorageRate());
                            DatabaseLicenseModel lic = GENERAL_PUBLIC_LICENSE;
                            if( "included".equalsIgnoreCase(databaseProduct.getLicense())) {
                                lic = LICENSE_INCLUDED;
                            } else if( "byol".equalsIgnoreCase(databaseProduct.getLicense())) {
                                lic = BRING_YOUR_OWN_LICENSE;
                            } else if( "postgres".equalsIgnoreCase(databaseProduct.getLicense())) {
                                lic = POSTGRESQL_LICENSE;
                            }
                            product.setLicenseModel(lic);
                            product.setCurrency(databaseProduct.getCurrency());
                            DatabaseProductDefinition def = databaseProvider.findProductDefinition(databaseProduct.getName());
                            if( def != null) {
                                product.setName(String.format("%.2fGB RAM, %d CPU, %s Network Performance", def.getMemory(), def.getvCpus(), def.getNetworkPerformance()));
                            }
                            product.setStorageInGigabytes(databaseProduct.getMinStorage());
                            products.add(product);
                        }
                    }
                }
            }
            return products.toArray(new DatabaseProduct[products.size()]);
        }
        catch (ProductReaderException pre) {
            throw new InternalException("Unable to read product configuration file " + pathToFile, pre);
        }
        finally {}
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize( String databaseSizeId ) {
        this.productSize = databaseSizeId;
    }

    public String getName() {
        return name;
    }

    public void setName( String databaseSizeName ) {
        this.name = databaseSizeName;
    }

    public void setEngine( DatabaseEngine engine ) {
        this.engine = engine;
    }

    public DatabaseEngine getEngine() {
        return engine;
    }

    public DatabaseLicenseModel getLicenseModel() {
        return licenseModel;
    }

    public void setLicenseModel( DatabaseLicenseModel licenseModel ) {
        this.licenseModel = licenseModel;
    }

    public void setProviderDataCenterId( String providerDataCenterId ) {
        this.providerDataCenterId = providerDataCenterId;
    }

    public String getProviderDataCenterId() {
        return providerDataCenterId;
    }

    public void setStorageInGigabytes( int storageInGigabytes ) {
        this.storageInGigabytes = storageInGigabytes;
    }

    public int getStorageInGigabytes() {
        return storageInGigabytes;
    }


    public float getStandardHourlyRate() {
        return standardHourlyRate;
    }

    public void setStandardHourlyRate( float standardHourlyRate ) {
        this.standardHourlyRate = standardHourlyRate;
    }

    public float getStandardStorageRate() {
        return standardStorageRate;
    }

    public void setStandardStorageRate( float standardStorageRate ) {
        this.standardStorageRate = standardStorageRate;
    }

    public void setStandardIoRate( float standardIoRate ) {
        this.standardIoRate = standardIoRate;
    }

    public float getStandardIoRate() {
        return standardIoRate;
    }

    public void setCurrency( String currency ) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public String toString() {
        return productSize;
    }

    public void setHighAvailability( boolean highAvailability ) {
        this.highAvailability = highAvailability;
    }

    public boolean isHighAvailability() {
        return highAvailability;
    }
}
