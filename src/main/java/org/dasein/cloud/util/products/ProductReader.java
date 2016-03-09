package org.dasein.cloud.util.products;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dasein.cloud.util.products.model.DatabaseProductProvider;
import org.dasein.cloud.util.products.model.VmProductProvider;
import org.dasein.cloud.util.products.model.VolumeProvider;

import java.io.IOException;
import java.net.URL;

/**
 * Created by mariapavlova on 17/02/2016.
 */
public class ProductReader {

    public static VmProductProvider readVmProducts( String filename, String providerId ) throws ProductReaderException {
        try {
            ObjectMapper om = new ObjectMapper();
            URL url = om.getClass().getResource(filename);
            VmProductProvider[] providers = om.readValue(url, VmProductProvider[].class);
            for( VmProductProvider provider : providers ) {
                if( provider.getProvider().equalsIgnoreCase(providerId) ) {
                    return provider;
                }
            }
        } catch( IOException e ) {
            throw new ProductReaderException("Unable to read stream", e);
        }
        throw new ProductReaderException("Unable to find "+providerId+" provider configuration in "+filename);
    }

    public static VolumeProvider readVolumeProducts(String filename, String providerId) throws ProductReaderException {
        try {
            ObjectMapper om = new ObjectMapper();
            URL url = om.getClass().getResource(filename);
            VolumeProvider[] providers = om.readValue(url, VolumeProvider[].class);
            for( VolumeProvider provider : providers ) {
                if( provider.getProvider().equalsIgnoreCase(providerId) ) {
                    return provider;
                }
            }
        } catch( IOException e ) {
            throw new ProductReaderException("Unable to read stream", e);
        }
        throw new ProductReaderException("Unable to find "+providerId+" provider configuration in "+filename);
    }

    public static DatabaseProductProvider readDatabaseProducts(String filename, String providerId) throws ProductReaderException {
        try {
            ObjectMapper om = new ObjectMapper();
            URL url = om.getClass().getResource(filename);
            DatabaseProductProvider[] providers = om.readValue(url, DatabaseProductProvider[].class);
            for( DatabaseProductProvider provider : providers ) {
                if( provider.getProvider().equalsIgnoreCase(providerId) ) {
                    return provider;
                }
            }
        } catch( IOException e ) {
            throw new ProductReaderException("Unable to read stream", e);
        }
        throw new ProductReaderException("Unable to find "+providerId+" provider configuration in "+filename);
    }

}
