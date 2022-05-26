package ru.geekbrains.webstore.services;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.geekbrains.webstore.entities.Role;
import ru.geekbrains.webstore.entities.User;
import ru.geekbrains.webstore.repositories.UserRepository;

@Service
@AllArgsConstructor
public class UserService implements ru.geekbrains.webstore.services.Service<User>, UserDetailsService {

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

  public Optional<User> findByUsername(String name) {
    return userRepository.findUserByUsername(name);
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
  }

  private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
    return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
  }
}
