package ru.geekbrains.webstore.auth.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.geekbrains.webstore.auth.mapper.ProfileMapper;
import ru.geekbrains.webstore.auth.service.ProfileService;
import java.security.Principal;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.webstore.api.dto.ProfileDto;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/profiles")
public class ProfileController {

  private ProfileService profileService;

  @GetMapping("/me")
  public ProfileDto getCurrentProfile(@RequestHeader String username) {
    return ProfileMapper.PROFILE_MAPPER.fromProfile(profileService.findByUsername(username));
  }

  @GetMapping
  public Page<ProfileDto> getAllUsers(@RequestParam(defaultValue = "1", name = "p") int pageIndex) {
    return profileService.findAll(pageIndex - 1, 10).map(ProfileMapper.PROFILE_MAPPER::fromProfile);
  }

  @GetMapping("/{id}")
  public ProfileDto GetUserById(@PathVariable Long id) {
    return ProfileMapper.PROFILE_MAPPER.fromProfile(profileService.findById(id));
  }

  @PostMapping
  public ProfileDto saveUser(@RequestBody ProfileDto profileDto) {
    return ProfileMapper.PROFILE_MAPPER.fromProfile(profileService.save(profileDto));
  }


  @PutMapping
  public ProfileDto updateUser(@RequestBody ProfileDto profileDto) {
    return ProfileMapper.PROFILE_MAPPER.fromProfile(profileService.update(profileDto));
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable Long id) {
    profileService.deleteById(id);
  }
}
