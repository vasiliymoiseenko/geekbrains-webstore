package ru.geekbrains.webstore.auth.service;

import ru.geekbrains.webstore.auth.entity.Profile;
import ru.geekbrains.webstore.auth.entity.Role;
import ru.geekbrains.webstore.auth.mapper.ProfileMapper;
import ru.geekbrains.webstore.auth.repository.ProfileRepository;
import ru.geekbrains.webstore.auth.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.webstore.api.dto.ProfileDto;
import ru.geekbrains.webstore.api.exception.ResourceNotFoundException;

@Service
@AllArgsConstructor
public class ProfileService {

  private ProfileRepository profileRepository;
  private RoleRepository roleRepository;

  public Page<Profile> findAll(int pageIndex, int pageSize) {
    if (pageIndex < 0) {
      pageIndex = 0;
    }
    return profileRepository.findAll(PageRequest.of(pageIndex, pageSize, Sort.by("id")));
  }

  public Profile findById(Long id) {
    return profileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Profile id = " + id + " not found"));
  }


  public void deleteById(Long id) {
    profileRepository.deleteById(id);
  }

  public Profile save(ProfileDto profileDto) {
    Profile profile = ProfileMapper.PROFILE_MAPPER.toProfile(profileDto);
    Role role = roleRepository.findRoleByName("ROLE_USER").orElseThrow(() -> new ResourceNotFoundException("ROLE_USER not found"));
    profile.getRoles().add(role);
    return profileRepository.save(profile);
  }

  public Profile update(ProfileDto profileDto) {
    Long id = profileDto.getId();
    Profile profile = profileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Profile id = " + id + " not found"));
    profile.setUsername(profileDto.getUsername());
    profile.setPassword(new BCryptPasswordEncoder().encode(profileDto.getPassword()));
    profile.setFirstName(profileDto.getFirstName());
    profile.setMiddleName(profileDto.getMiddleName());
    profile.setLastName(profileDto.getLastName());
    profile.setEmail(profileDto.getEmail());
    profile.setPhone(profileDto.getPhone());
    return profileRepository.save(profile);
  }

  public Profile findByUsername(String username) {
    return profileRepository.findProfileByUsername(username)
        .orElseThrow(() -> new ResourceNotFoundException("Profile username = " + username + " not found"));
  }
}
