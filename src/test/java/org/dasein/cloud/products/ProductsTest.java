package org.dasein.cloud.products;

import org.dasein.cloud.Cloud;
import org.dasein.cloud.ProviderContext;
import org.dasein.cloud.compute.VirtualMachineProduct;
import org.dasein.cloud.compute.VolumeProduct;
import org.dasein.cloud.platform.DatabaseProduct;
import org.dasein.cloud.util.products.ProductReader;
import org.dasein.cloud.util.products.model.VmProductProvider;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by mariapavlova on 23/02/2016.
 */
@RunWith(PowerMockRunner.class)
public class ProductsTest {

    private static ProviderContext contextMock;
    private static Cloud cloudMock;

    @BeforeClass
    public static void setupClass() {
        contextMock = mock(ProviderContext.class);
        cloudMock = mock(Cloud.class);
        when(contextMock.getRegionId()).thenReturn("eu-west-1");
        when(contextMock.getCloud()).thenReturn(cloudMock);
        when(cloudMock.getProviderName()).thenReturn("AWS");
    }

    @Test
    public void readVmProducts() throws Throwable {
        VmProductProvider vmProductProvider = ProductReader.readVmProducts("/org/dasein/cloud/products/vmproducts.json", "AWS");
        assertNotNull(vmProductProvider);
    }

    @Test
    public void readVolumeProducts() throws Throwable {
        VolumeProduct[] products = VolumeProduct.fromConfigurationFile("/org/dasein/cloud/products/volproducts.json", contextMock);
        assertNotNull("Result can't be null", products);
        assertEquals("Invalid number of products is returned", 3, products.length);
    }

    @Test
    public void readVirtualMachineProducts() throws Exception {
        VirtualMachineProduct[] products = VirtualMachineProduct.fromConfigurationFile("/org/dasein/cloud/products/vmproducts.json", contextMock);
        assertNotNull("Result can't be null", products);
        assertEquals("Invalid number of products is returned", 53, products.length);
    }

    @Test
    public void readDatabaseProducts() throws Exception {
        DatabaseProduct[] products = DatabaseProduct.fromConfigurationFile("/org/dasein/cloud/products/dbproducts.json", contextMock);
        assertNotNull("Result can't be null", products);
        assertEquals("Invalid number of products is returned", 166, products.length);
    }

}
