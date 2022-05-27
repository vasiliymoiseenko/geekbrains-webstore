package ru.geekbrains.webstore.controller;

import static ru.geekbrains.webstore.mapper.UserMapper.USER_MAPPER;

import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.webstore.dto.UserDto;
import ru.geekbrains.webstore.entity.User;
import ru.geekbrains.webstore.exception.DataValidationException;
import ru.geekbrains.webstore.exception.ResourceNotFoundException;
import ru.geekbrains.webstore.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

  private UserService userService;

  @GetMapping
  public Page<UserDto> getAllUsers(@RequestParam(defaultValue = "1", name = "p") int pageIndex) {
    return userService.findAll(pageIndex - 1, 10).map(USER_MAPPER::fromUser);
  }

  @GetMapping("/{id}")
  public UserDto GetUserById(@PathVariable Long id) {
    return USER_MAPPER.fromUser(userService.findById(id));
  }

  @PostMapping
  public UserDto saveUser(@RequestBody @Validated UserDto userDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new DataValidationException(bindingResult.getAllErrors().stream()
          .map(ObjectError::getDefaultMessage)
          .collect(Collectors.toList()));
    }
    return USER_MAPPER.fromUser(userService.save(userDto));
  }

  @PutMapping
  public UserDto updateUser(@RequestBody @Validated UserDto userDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new DataValidationException(bindingResult.getAllErrors().stream()
          .map(ObjectError::getDefaultMessage)
          .collect(Collectors.toList()));
    }
    return USER_MAPPER.fromUser(userService.update(userDto));
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable Long id) {
    userService.deleteById(id);
  }
}
