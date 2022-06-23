package ru.geekbrains.webstore.core.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.webstore.core.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

  Optional<Profile> findProfileByUsername(String username);
}
