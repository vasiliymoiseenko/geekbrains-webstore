package ru.geekbrains.webstore.core.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import ru.geekbrains.webstore.core.exception.DataValidationException;
import ru.geekbrains.webstore.core.mapper.ProductMapper;
import ru.geekbrains.webstore.core.service.ProductService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.webstore.api.dto.ProductDto;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {

  private ProductService productService;

  @GetMapping
  public Page<ProductDto> getAllProducts(
      @RequestParam(defaultValue = "1", name = "p") int pageIndex,
      @RequestParam MultiValueMap<String, String> params) {
    return productService.findAll(params, pageIndex - 1, 10).map(ProductMapper.PRODUCT_MAPPER::fromProduct);
  }

  @GetMapping("/{id}")
  public ProductDto getProductById(@PathVariable Long id) {
    return ProductMapper.PRODUCT_MAPPER.fromProduct(productService.findById(id));
  }

  @PostMapping
  public ProductDto saveProduct(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new DataValidationException(bindingResult.getAllErrors().stream()
          .map(ObjectError::getDefaultMessage)
          .collect(Collectors.toList()));
    }
    return ProductMapper.PRODUCT_MAPPER.fromProduct(productService.save(productDto));
  }

  @PutMapping
  public ProductDto updateProduct(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new DataValidationException(bindingResult.getAllErrors().stream()
          .map(ObjectError::getDefaultMessage)
          .collect(Collectors.toList()));
    }
    return ProductMapper.PRODUCT_MAPPER.fromProduct(productService.update(productDto));
  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable Long id) {
    productService.deleteById(id);
  }

  @GetMapping("/filter")
  public List<ProductDto> filterProducts(@RequestParam(required = false) Long minPrice, @RequestParam(required = false) Long maxPrice) {
    return productService.findAllByPrice(minPrice, maxPrice).stream().map(ProductMapper.PRODUCT_MAPPER::fromProduct).collect(Collectors.toList());
  }
}
