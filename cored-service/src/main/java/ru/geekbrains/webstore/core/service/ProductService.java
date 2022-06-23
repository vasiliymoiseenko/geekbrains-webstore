package ru.geekbrains.webstore.core.service;

import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import ru.geekbrains.webstore.api.dto.ProductDto;
import ru.geekbrains.webstore.api.exception.ResourceNotFoundException;
import ru.geekbrains.webstore.core.entity.Product;
import ru.geekbrains.webstore.core.mapper.ProductMapper;
import ru.geekbrains.webstore.core.repository.ProductRepository;
import ru.geekbrains.webstore.core.repository.specification.ProductSpecification;

@Service
@AllArgsConstructor
public class ProductService {

  private ProductRepository productRepository;

  public Page<Product> findAll(MultiValueMap<String, String> params, int pageIndex, int pageSize) {
    if (pageIndex < 0) {
      pageIndex = 0;
    }
    return productRepository.findAll(ProductSpecification.construct(params), PageRequest.of(pageIndex, pageSize, Sort.by("id")));
  }

  public Product findById(Long id) {
    return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product id = " + id + " not found"));
  }

  public void deleteById(Long id) {
    productRepository.deleteById(id);
  }

  public Product save(ProductDto productDto) {
    Product product = ProductMapper.PRODUCT_MAPPER.toProduct(productDto);
    return productRepository.save(product);
  }

  public Product update(ProductDto productDto) {
    Long id = productDto.getId();
    Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product id = " + id + " not found"));
    product.setTitle(productDto.getTitle());
    product.setPrice(productDto.getPrice());
    return productRepository.save(product);
  }

  public List<Product> findAllByPrice(Long minPrice, Long maxPrice) {
    if (minPrice == null && maxPrice == null) {
      return Collections.emptyList();
    }
    if (minPrice != null && maxPrice != null) {
      return productRepository.findAllByPriceBetween(minPrice, maxPrice);
    }
    if (minPrice == null) {
      return productRepository.findAllByPriceIsLessThanEqual(maxPrice);
    }
    return productRepository.findAllByPriceGreaterThanEqual(minPrice);
  }

  public Product findByTitle(String title) {
    return productRepository.findProductByTitle(title)
        .orElseThrow(() -> new ResourceNotFoundException("Product title = " + title + " not found"));
  }
}