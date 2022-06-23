package ru.geekbrains.webstore.core.mapper;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.webstore.api.dto.ProfileDto;
import ru.geekbrains.webstore.core.entity.Profile;

@Mapper
public interface ProfileMapper {

  ProfileMapper PROFILE_MAPPER = Mappers.getMapper(ProfileMapper.class);

  //@Mapping(source = "roles", target = "roles")
  Profile toProfile(ProfileDto dto);

  @InheritInverseConfiguration
  ProfileDto fromProfile(Profile profile);

  List<Profile> toProfileList(List<ProfileDto> profileDtoList);

  List<ProfileDto> fromProfileList(List<Profile> profile);
}
