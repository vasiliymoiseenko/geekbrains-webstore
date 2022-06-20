package ru.geekbrains.webstore.soap.endpoint;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.webstore.entity.Product;
import ru.geekbrains.webstore.repository.ProductRepository;
import ru.geekbrains.webstore.soap.product.GetAllProductsRequest;
import ru.geekbrains.webstore.soap.product.GetAllProductsResponse;
import ru.geekbrains.webstore.soap.product.GetProductByNameRequest;
import ru.geekbrains.webstore.soap.product.GetProductByNameResponse;
import ru.geekbrains.webstore.soap.product.ProductSoap;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {

  private static final String NAMESPACE_URI = "http://www.moiseenko.com/spring/ws/products";

  //Поменял сервис на репозиторий, так как в процессе доработки магазина в сервисе менялись аргументы методов
  private final ProductRepository productRepository;

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByNameRequest")
  @ResponsePayload
  public GetProductByNameResponse getProductByName(@RequestPayload GetProductByNameRequest request) {
    GetProductByNameResponse response = new GetProductByNameResponse();
    Product product = productRepository.findProductByTitle(request.getName()).get();
    ProductSoap productSoap = new ProductSoap();
    productSoap.setId(product.getId());
    productSoap.setTitle(product.getTitle());
    productSoap.setPrice(product.getPrice());
    response.setProduct(productSoap);
    return response;
  }

    /*
        Пример запроса: POST http://localhost:8189/webstore/ws
        Header -> Content-Type: text/xml

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.moiseenko.com/spring/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
  @ResponsePayload
  public GetAllProductsResponse getAllStudents(@RequestPayload GetAllProductsRequest request) {
    GetAllProductsResponse response = new GetAllProductsResponse();
    List<Product> listProducts = productRepository.findAll();
    for (Product product : listProducts) {
      ProductSoap productSoap = new ProductSoap();
      productSoap.setId(product.getId());
      productSoap.setTitle(product.getTitle());
      productSoap.setPrice(product.getPrice());
      response.getProducts().add(productSoap);
    }
    return response;
  }
}
