package com.samsonmarikwa.restservices.controllers;

import com.samsonmarikwa.restservices.dtos.UserDtoV1;
import com.samsonmarikwa.restservices.dtos.UserDtoV2;
import com.samsonmarikwa.restservices.entities.User;
import com.samsonmarikwa.restservices.exceptions.UserNotFoundException;
import com.samsonmarikwa.restservices.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequestMapping("/versioning/params/users")
public class UserRequestParamsVersioningController {
   
   @Autowired
   private UserService userService;
   
   @Autowired
   private ModelMapper modelMapper;
   
   // Request Parameter based Versioning - V1
   @GetMapping(value = "/{id}", params = "version=1")
   public UserDtoV1 getUserById(@PathVariable @Min(1) long id) throws UserNotFoundException {  // @Min requires @Validated annotation at class level
      Optional<User> userOptional = userService.getUserById(id);
      
      if(!userOptional.isPresent()) {
         throw new UserNotFoundException("User Not Found");
      }
      
      User user = userOptional.get();
   
      UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);
      return userDtoV1;
   }
   
   // Request Parameter based Versioning - V2
   @GetMapping(value = "/{id}", params = "version=2")
   public UserDtoV2 getUserById2(@PathVariable @Min(1) long id) throws UserNotFoundException {  // @Min requires @Validated annotation at class level
      Optional<User> userOptional = userService.getUserById(id);
      
      if(!userOptional.isPresent()) {
         throw new UserNotFoundException("User Not Found");
      }
      
      User user = userOptional.get();
   
      UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);
      return userDtoV2;
   }
}
