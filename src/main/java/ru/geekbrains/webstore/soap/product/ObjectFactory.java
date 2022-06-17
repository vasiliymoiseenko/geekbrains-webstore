
package ru.geekbrains.webstore.soap.product;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.geekbrains.webstore.soap.product package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.geekbrains.webstore.soap.product
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetProductByNameRequest }
     * 
     */
    public GetProductByNameRequest createGetProductByNameRequest() {
        return new GetProductByNameRequest();
    }

    /**
     * Create an instance of {@link GetAllProductsRequest }
     * 
     */
    public GetAllProductsRequest createGetAllProductsRequest() {
        return new GetAllProductsRequest();
    }

    /**
     * Create an instance of {@link GetProductByNameResponse }
     * 
     */
    public GetProductByNameResponse createGetProductByNameResponse() {
        return new GetProductByNameResponse();
    }

    /**
     * Create an instance of {@link ProductSoap }
     * 
     */
    public ProductSoap createProductSoap() {
        return new ProductSoap();
    }

    /**
     * Create an instance of {@link GetAllProductsResponse }
     * 
     */
    public GetAllProductsResponse createGetAllProductsResponse() {
        return new GetAllProductsResponse();
    }

}