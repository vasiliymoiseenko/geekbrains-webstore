package ru.geekbrains.webstore.mapper;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.webstore.dto.ProductDto;
import ru.geekbrains.webstore.entity.Product;

@Mapper
public interface ProductMapper {

  ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

  Product toProduct(ProductDto dto);

  @InheritInverseConfiguration
  ProductDto fromProduct(Product product);

  List<Product> toProductList(List<ProductDto> productDtoList);

  List<ProductDto> fromProductList(List<Product> productList);
}
