package ru.geekbrains.webstore.core.repository;

import ru.geekbrains.webstore.core.entity.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

  Optional<Category> findCategoryByTitle(String title);
}
