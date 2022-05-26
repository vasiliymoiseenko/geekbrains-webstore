package ru.geekbrains.webstore.controllers;

import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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
import ru.geekbrains.webstore.dtos.UserDto;
import ru.geekbrains.webstore.entities.User;
import ru.geekbrains.webstore.exceptions.DataValidationException;
import ru.geekbrains.webstore.exceptions.ResourceNotFoundException;
import ru.geekbrains.webstore.services.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

  private UserService userService;

  @GetMapping
  public Page<UserDto> getAllUsers(@RequestParam(defaultValue = "1", name = "p") int pageIndex) {
    if (pageIndex < 1) {
      pageIndex = 1;
    }
    return userService.findAll(pageIndex - 1, 10).map(UserDto::new);
  }

  @GetMapping("/{id}")
  public UserDto GetUserById(@PathVariable Long id) {
    User user = userService.findById(id).orElseThrow(() -> new ResourceNotFoundException("User id = " + id + " not found"));
    return new UserDto(user);
  }

  @PostMapping
  public UserDto addUser(@RequestBody @Validated UserDto userDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new DataValidationException(bindingResult.getAllErrors().stream()
          .map(ObjectError::getDefaultMessage)
          .collect(Collectors.toList()));
    }

    User user = new User();
    user.setName(userDto.getName());
    userService.save(user);
    return new UserDto(user);
  }

  @PutMapping
  public UserDto updateUser(@RequestBody @Validated UserDto userDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new DataValidationException(bindingResult.getAllErrors().stream()
          .map(ObjectError::getDefaultMessage)
          .collect(Collectors.toList()));
    }

    Long id = userDto.getId();
    User user = userService.findById(id).orElseThrow(() -> new ResourceNotFoundException("User id = " + id + " not found"));
    user.setName(userDto.getName());
    userService.save(user);
    return new UserDto(user);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable Long id) {
    userService.deleteById(id);
  }
}
