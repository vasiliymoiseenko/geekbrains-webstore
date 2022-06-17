package ru.geekbrains.webstore.controller;

import static ru.geekbrains.webstore.mapper.CategoryMapper.CATEGORY_MAPPER;

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
import ru.geekbrains.webstore.dto.CategoryDto;
import ru.geekbrains.webstore.exception.DataValidationException;
import ru.geekbrains.webstore.service.CategoryService;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  public List<CategoryDto> getAllCategories() {
    return CATEGORY_MAPPER.fromCategoryList(categoryService.findAll());
  }

  @GetMapping("/{id}")
  public CategoryDto getCategoryById(@PathVariable Long id) {
    return CATEGORY_MAPPER.fromCategory(categoryService.findById(id));
  }

  @PostMapping
  public CategoryDto saveCategory(@RequestBody @Validated CategoryDto categoryDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new DataValidationException(bindingResult.getAllErrors().stream()
          .map(ObjectError::getDefaultMessage)
          .collect(Collectors.toList()));
    }
    return CATEGORY_MAPPER.fromCategory(categoryService.save(categoryDto));
  }

  @PutMapping
  public CategoryDto updateCategory(@RequestBody @Validated CategoryDto categoryDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new DataValidationException(bindingResult.getAllErrors().stream()
          .map(ObjectError::getDefaultMessage)
          .collect(Collectors.toList()));
    }
    return CATEGORY_MAPPER.fromCategory(categoryService.update(categoryDto));
  }

  @DeleteMapping("/{id}")
  public void deleteCategory(@PathVariable Long id) {
    categoryService.deleteById(id);
  }


}
