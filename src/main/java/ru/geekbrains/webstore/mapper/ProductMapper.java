package ru.geekbrains.webstore.mapper;

import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.webstore.dto.ProductDto;
import ru.geekbrains.webstore.entity.OrderItem;
import ru.geekbrains.webstore.entity.Product;

@Mapper
public interface ProductMapper {

  ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

  Product toProduct(ProductDto dto);

  @Mapping(target = "comments", source = "items", qualifiedByName = "getComments")
  ProductDto fromProduct(Product product);

  @Named("getComments")
  default List<String> getComments(List<OrderItem> items) {
    List<String> comments = new ArrayList<>();
    for (OrderItem item : items) {
      if (item.getComment() != null) {
        comments.add(item.getComment());
      }
    }
    return comments;
  }

  List<Product> toProductList(List<ProductDto> productDtoList);

  List<ProductDto> fromProductList(List<Product> productList);
}
