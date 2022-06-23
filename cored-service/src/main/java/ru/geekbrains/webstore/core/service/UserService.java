package ru.geekbrains.webstore.core.service;

import java.util.Collection;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.webstore.api.exception.ResourceNotFoundException;
import ru.geekbrains.webstore.core.entity.Role;
import ru.geekbrains.webstore.core.entity.User;
import ru.geekbrains.webstore.core.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

  private UserRepository userRepository;

  public User findByUsername(String name) {
    return userRepository.findUserByUsername(name).orElseThrow(() -> new ResourceNotFoundException("Username = " + name + " not found"));
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = findByUsername(username);
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
  }

  private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
    return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
  }
}
