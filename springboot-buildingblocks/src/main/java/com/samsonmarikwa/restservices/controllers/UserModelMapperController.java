package com.samsonmarikwa.restservices.controllers;

import com.samsonmarikwa.restservices.dtos.UserMmDto;
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
@RequestMapping("/modelmapper/users")
public class UserModelMapperController {
   
   @Autowired
   private UserService userService;
   
   @Autowired
   private ModelMapper modelMapper;
   
   @GetMapping("/{id}")
   public UserMmDto getUserById(@PathVariable @Min(1) long id) throws UserNotFoundException {  // @Min requires @Validated annotation at class level
      Optional<User> userOptional = userService.getUserById(id);
      
      if(!userOptional.isPresent()) {
         throw new UserNotFoundException("User Not Found");
      }
      
      User user = userOptional.get();
   
      UserMmDto userMmDto = modelMapper.map(user, UserMmDto.class);
      return userMmDto;
   }
}
