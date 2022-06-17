package ru.geekbrains.webstore.service;

import static ru.geekbrains.webstore.mapper.CategoryMapper.CATEGORY_MAPPER;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.webstore.dto.CategoryDto;
import ru.geekbrains.webstore.entity.Category;
import ru.geekbrains.webstore.exception.ResourceNotFoundException;
import ru.geekbrains.webstore.repository.CategoryRepository;

@Service
@AllArgsConstructor
public class CategoryService {

  private CategoryRepository categoryRepository;

  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  public Category findById(Long id) {
    return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category id = " + id + " not found"));
  }

  public Category save(CategoryDto categoryDto) {
    Category category = CATEGORY_MAPPER.toCategory(categoryDto);
    return categoryRepository.save(category);
  }

  public Category update(CategoryDto categoryDto) {
    Long id = categoryDto.getId();
    Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category id = " + id + " not found"));
    category.setTitle(categoryDto.getTitle());
    return categoryRepository.save(category);
  }

  public void deleteById(Long id) {
    categoryRepository.deleteById(id);
  }
}
