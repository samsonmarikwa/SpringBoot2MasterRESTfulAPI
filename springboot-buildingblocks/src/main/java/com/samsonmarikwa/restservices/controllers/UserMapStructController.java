package com.samsonmarikwa.restservices.controllers;

import com.samsonmarikwa.restservices.dtos.UserMsDto;
import com.samsonmarikwa.restservices.entities.User;
import com.samsonmarikwa.restservices.exceptions.UserNotFoundException;
import com.samsonmarikwa.restservices.mappers.UserMapper;
import com.samsonmarikwa.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mapstruct/users")
public class UserMapStructController {
   
   @Autowired
   private UserRepository userRepository;
   
   @Autowired
   private UserMapper userMapper;
   
   @GetMapping
   public List<UserMsDto> getAllUsers() {
      return userMapper.usersToUserMsDtos(userRepository.findAll());
   }
   
   @GetMapping("/{id}")
   public UserMsDto getUserById(@PathVariable @Min(1) long id) {  // @Min requires @Validated annotation at class level
      try {
         Optional<User> userOptional = userRepository.findById(id);
         if(!userOptional.isPresent()) {
            throw new UserNotFoundException("User Not Found");
         }
         return userMapper.userToUserMsDto(userOptional.get());
      } catch (UserNotFoundException ex) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
      }
   }
}
