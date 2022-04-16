package com.samsonmarikwa.restservices.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.samsonmarikwa.restservices.entities.User;
import com.samsonmarikwa.restservices.entities.Views;
import com.samsonmarikwa.restservices.exceptions.UserNotFoundException;
import com.samsonmarikwa.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "jsonview/users")
public class UserJsonViewController {
   
   @Autowired
   private UserService userService;
   
   // External view
   @JsonView(Views.External.class)
   @GetMapping("/external/{id}")
   public Optional<User> getUserById(@PathVariable @Min(1) long id) {  // @Min requires @Validated annotation at class level
      try {
         return userService.getUserById(id);
      } catch (UserNotFoundException ex) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
      }
   }
   
   // Internal View
   @JsonView(Views.Internal.class)
   @GetMapping("/internal/{id}")
   public Optional<User> getUserById2(@PathVariable @Min(1) long id) {  // @Min requires @Validated annotation at class level
      try {
         return userService.getUserById(id);
      } catch (UserNotFoundException ex) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
      }
   }
   
   
}
