package ru.geekbrains.webstore.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;
import ru.geekbrains.webstore.entity.Product;

public class ProductSpecification {

  private static final String FILTER_MIN_PRICE = "min_price";
  private static final String FILTER_MAX_PRICE = "max_price";
  private static final String FILTER_TITLE = "title";

  public static Specification<Product> construct(MultiValueMap<String, String> params) {
    Specification<Product> specification = Specification.where(null);
    if (params.containsKey(FILTER_MIN_PRICE) && !params.getFirst(FILTER_MIN_PRICE).isBlank()) {
      int minPrice = Integer.parseInt(params.getFirst(FILTER_MIN_PRICE));
      specification = specification.and(priceGreaterOrEqualsThan(minPrice));
    }
    if (params.containsKey(FILTER_MAX_PRICE) && !params.getFirst(FILTER_MAX_PRICE).isBlank()) {
      int maxPrice = Integer.parseInt(params.getFirst(FILTER_MAX_PRICE));
      specification = specification.and(priceLesserOrEqualsThan(maxPrice));
    }
    if (params.containsKey(FILTER_TITLE) && !params.getFirst(FILTER_TITLE).isBlank()) {
      String title = params.getFirst(FILTER_TITLE);
      specification = specification.and(titleLike(title));
    }
    return specification;
  }

  private static Specification<Product> priceGreaterOrEqualsThan(int minPrice) {
    return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
  }

  private static Specification<Product> priceLesserOrEqualsThan(int maxPrice) {
    return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
  }

  private static Specification<Product> titleLike(String title) {
    return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + title + "%");
  }


}
