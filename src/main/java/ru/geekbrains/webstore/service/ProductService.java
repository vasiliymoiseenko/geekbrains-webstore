package ru.geekbrains.webstore.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.geekbrains.webstore.dto.ProductDto;
import ru.geekbrains.webstore.entity.Product;
import ru.geekbrains.webstore.exception.ResourceNotFoundException;
import ru.geekbrains.webstore.mapper.ProductMapper;
import ru.geekbrains.webstore.repository.ProductRepository;

@Service
@AllArgsConstructor
public class ProductService {

  private ProductRepository productRepository;

  public Page<Product> findAll(int pageIndex, int pageSize) {
    if (pageIndex < 0) {
      pageIndex = 0;
    }
    return productRepository.findAll(PageRequest.of(pageIndex, pageSize, Sort.by("id")));
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

  public Optional<Product> findByTitle(String title) {
    return productRepository.findProductByTitle(title);
  }
}