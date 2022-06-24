package ru.geekbrains.webstore.core.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import ru.geekbrains.webstore.core.exception.DataValidationException;
import ru.geekbrains.webstore.core.mapper.CategoryMapper;
import ru.geekbrains.webstore.core.service.CategoryService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.webstore.api.dto.CategoryDto;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  public List<CategoryDto> getAllCategories() {
    return CategoryMapper.CATEGORY_MAPPER.fromCategoryList(categoryService.findAll());
  }

  @GetMapping("/{id}")
  public CategoryDto getCategoryById(@PathVariable Long id) {
    return CategoryMapper.CATEGORY_MAPPER.fromCategory(categoryService.findById(id));
  }

  @PostMapping
  public CategoryDto saveCategory(@RequestBody @Validated CategoryDto categoryDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new DataValidationException(bindingResult.getAllErrors().stream()
          .map(ObjectError::getDefaultMessage)
          .collect(Collectors.toList()));
    }
    return CategoryMapper.CATEGORY_MAPPER.fromCategory(categoryService.save(categoryDto));
  }

  @PutMapping
  public CategoryDto updateCategory(@RequestBody @Validated CategoryDto categoryDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new DataValidationException(bindingResult.getAllErrors().stream()
          .map(ObjectError::getDefaultMessage)
          .collect(Collectors.toList()));
    }
    return CategoryMapper.CATEGORY_MAPPER.fromCategory(categoryService.update(categoryDto));
  }

  @DeleteMapping("/{id}")
  public void deleteCategory(@PathVariable Long id) {
    categoryService.deleteById(id);
  }


}
