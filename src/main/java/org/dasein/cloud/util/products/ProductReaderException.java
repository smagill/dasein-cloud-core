package org.dasein.cloud.util.products;

/**
 * Created by mariapavlova on 18/02/2016.
 */
public class ProductReaderException extends Throwable {
    public ProductReaderException( String message ) {
        super(message);
    }

    public ProductReaderException( String message, Throwable cause ) {
        super(message, cause);
    }
}
