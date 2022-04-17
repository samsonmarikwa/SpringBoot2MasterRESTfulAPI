package com.samsonmarikwa.restservices.mappers;

import com.samsonmarikwa.restservices.dtos.UserMsDto;
import com.samsonmarikwa.restservices.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {
   
   UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
   
   // User Object to User DTO
   @Mappings({
         @Mapping(source = "email", target = "emailAddress"),
         @Mapping(source = "role", target = "roleName")
   })
   UserMsDto userToUserMsDto(User user);
   
   
   // List<User> to List<UserMsDto>
   List<UserMsDto> usersToUserMsDtos(List<User> users);
   
}
