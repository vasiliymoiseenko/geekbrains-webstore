package ru.geekbrains.webstore.core.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.webstore.core.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

  Optional<Status> findStatusByTitle(String title);

}
