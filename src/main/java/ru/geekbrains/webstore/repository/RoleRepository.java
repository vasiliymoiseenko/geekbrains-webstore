package ru.geekbrains.webstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.webstore.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
