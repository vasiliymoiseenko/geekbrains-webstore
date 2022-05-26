package ru.geekbrains.webstore.services;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.geekbrains.webstore.entities.User;
import ru.geekbrains.webstore.repositories.UserRepository;

@Service
@AllArgsConstructor
public class UserService implements ru.geekbrains.webstore.services.Service<User> {

  private UserRepository userRepository;

  @Override
  public Page<User> findAll(int pageIndex, int pageSize) {
    return userRepository.findAll(PageRequest.of(pageIndex, pageSize, Sort.by("id")));
  }

  @Override
  public Optional<User> findById(Long id) {
    return userRepository.findById(id);
  }

  @Override
  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }

  @Override
  public void save(User user) {
    userRepository.save(user);
  }

  public Optional<User> findByName(String name) {
    return userRepository.findUserByName(name);
  }
}
