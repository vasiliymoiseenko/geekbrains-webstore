package ru.geekbrains.webstore.mapper;

import static ru.geekbrains.webstore.mapper.ProductMapper.PRODUCT_MAPPER;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.webstore.dto.CategoryDto;
import ru.geekbrains.webstore.dto.ProductDto;
import ru.geekbrains.webstore.entity.Category;
import ru.geekbrains.webstore.entity.Product;

@Mapper
public interface CategoryMapper {

  CategoryMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryMapper.class);

  @Mapping(target = "products", source = "products", qualifiedByName = "toProductList")
  Category toCategory(CategoryDto dto);

  @Named("toProductList")
  default List<Product> toProductList(List<ProductDto> productsDto) {
    return PRODUCT_MAPPER.toProductList(productsDto);
  }

  @Mapping(target = "products", source = "products", qualifiedByName = "fromProductList")
  CategoryDto fromCategory(Category category);

  @Named("fromProductList")
  default List<ProductDto> fromProductList(List<Product> products) {
    return PRODUCT_MAPPER.fromProductList(products);
  }


  List<Category> toCategoryList(List<CategoryDto> categoryDtoList);

  List<CategoryDto> fromCategoryList(List<Category> categoryList);

}
