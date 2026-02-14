package com.example.mylearning.dto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.example.mylearning.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper{

    // User → UserMsDto
	@Mappings({
		@Mapping(source= "firstname", target="userFirstName"),
		@Mapping(source = "city", target="userCity")
		})

    UserMsDto userToUserMsDto(User user);

    // List<User> → List<UserMsDto>
    List<UserMsDto> usersToUserDtos(List<User> users);
}
