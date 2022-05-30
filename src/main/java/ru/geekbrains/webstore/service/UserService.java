package ru.geekbrains.webstore.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.webstore.dto.UserDto;
import ru.geekbrains.webstore.entity.Role;
import ru.geekbrains.webstore.entity.User;
import ru.geekbrains.webstore.exception.ResourceNotFoundException;
import ru.geekbrains.webstore.repository.RoleRepository;
import ru.geekbrains.webstore.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

  private UserRepository userRepository;
  private RoleRepository roleRepository;

  public Page<User> findAll(int pageIndex, int pageSize) {
    if (pageIndex < 0) {
      pageIndex = 0;
    }
    return userRepository.findAll(PageRequest.of(pageIndex, pageSize, Sort.by("id")));
  }

  public User findById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User id = " + id + " not found"));
  }

  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }

  public User save(UserDto userDto) {
    User user = new User();
    user.setUsername(userDto.getUsername());
    user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
    Role role = roleRepository.findRoleByName("ROLE_USER").orElseThrow(() -> new ResourceNotFoundException("ROLE_USER not found"));
    user.setRoles(new ArrayList<>());
    user.getRoles().add(role);
    return userRepository.save(user);
  }

  public User update(UserDto userDto) {
    Long id = userDto.getId();
    User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User id = " + id + " not found"));
    user.setUsername(userDto.getUsername());
    user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
    return userRepository.save(user);
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
