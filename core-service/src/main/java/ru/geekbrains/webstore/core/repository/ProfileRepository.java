package ru.geekbrains.webstore.core.repository;

import ru.geekbrains.webstore.core.entity.Profile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

  Optional<Profile> findProfileByUsername(String username);
}
