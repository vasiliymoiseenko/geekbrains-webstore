package ru.geekbrains.webstore.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.webstore.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findRoleByName(String name);
}
