package ru.geekbrains.webstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.webstore.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
