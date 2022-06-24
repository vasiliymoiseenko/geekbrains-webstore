package ru.geekbrains.webstore.core.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import ru.geekbrains.webstore.core.exception.DataValidationException;
import ru.geekbrains.webstore.core.mapper.ProfileMapper;
import ru.geekbrains.webstore.core.service.ProfileService;
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
@CrossOrigin("*")
public class ProfileController {

  private ProfileService profileService;

  @GetMapping("/me")
  public ProfileDto getCurrentProfile(Principal principal) {
    return ProfileMapper.PROFILE_MAPPER.fromProfile(profileService.findByUsername(principal.getName()));
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
  public ProfileDto updateUser(@RequestBody @Validated ProfileDto profileDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new DataValidationException(bindingResult.getAllErrors().stream()
          .map(ObjectError::getDefaultMessage)
          .collect(Collectors.toList()));
    }
    return ProfileMapper.PROFILE_MAPPER.fromProfile(profileService.update(profileDto));
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable Long id) {
    profileService.deleteById(id);
  }
}
