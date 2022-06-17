package ru.geekbrains.webstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.webstore.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
