package ru.geekbrains.webstore.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.webstore.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findUserByName(String name);
}
