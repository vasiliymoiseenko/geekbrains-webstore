package ru.geekbrains.webstore.controllers;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.webstore.dtos.ProductDto;
import ru.geekbrains.webstore.entities.Product;
import ru.geekbrains.webstore.services.ProductService;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {

  private ProductService productService;

  @GetMapping
  public Page<ProductDto> findAll(@RequestParam(defaultValue = "1", name = "p") int pageIndex) {
    if (pageIndex < 1) {
      pageIndex = 1;
    }
    return productService.findAll(pageIndex - 1, 10).map(ProductDto::new);
  }

  @GetMapping("/{id}")
  public ProductDto findById(@PathVariable Long id) {
    return new ProductDto(productService.findById(id).get());
  }

  @PostMapping
  public ProductDto save(@RequestBody ProductDto productDto) {
    Product product = new Product();
    product.setTitle(productDto.getTitle());
    product.setPrice(productDto.getPrice());
    productService.save(product);
    return new ProductDto(product);
  }

  @PutMapping
  public ProductDto update(@RequestBody ProductDto productDto) {
    Product product = new Product();
    product.setId(productDto.getId());
    product.setTitle(productDto.getTitle());
    product.setPrice(productDto.getPrice());
    productService.save(product);
    return new ProductDto(product);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    productService.deleteById(id);
  }

  @GetMapping("/filter")
  public List<ProductDto> filter(@RequestParam(required = false) Double minPrice, @RequestParam(required = false) Double maxPrice) {
    return productService.findAllByPrice(minPrice, maxPrice).stream().map(ProductDto::new).collect(Collectors.toList());
  }
}
