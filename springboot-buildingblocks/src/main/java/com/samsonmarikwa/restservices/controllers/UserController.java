package com.samsonmarikwa.restservices.controllers;

import com.samsonmarikwa.restservices.entities.User;
import com.samsonmarikwa.restservices.exceptions.UserExistsException;
import com.samsonmarikwa.restservices.exceptions.UserNotFoundException;
import com.samsonmarikwa.restservices.exceptions.UsernameNotFoundException;
import com.samsonmarikwa.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/users")
public class UserController {
   
   @Autowired
   private UserService userService;
   
   @GetMapping
   public List<User> getAllUsers() {
      return userService.getAllUsers();
   }
   
   @PostMapping
   // @Valid is required for JSR Bean validation to kick in. It works with @NotEmpty etc. implemented in the POJO
   public ResponseEntity<?> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {
      try {
         userService.createUser(user);
         HttpHeaders httpHeaders = new HttpHeaders();
         httpHeaders.setLocation(builder.path("/users/{id}").buildAndExpand(user.getUserId()).toUri());
         return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
      } catch (UserExistsException ex) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
      }
   }
   
   @GetMapping("/{id}")
   public Optional<User> getUserById(@PathVariable @Min(1) long id) {  // @Min requires @Validated annotation at class level
      try {
         return userService.getUserById(id);
      } catch (UserNotFoundException ex) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
      }
   }
   
   @PutMapping("/{id}")
   public User updateUserById(@PathVariable long id, @RequestBody User user) {
      try {
         return userService.updateUserById(id, user);
      } catch (UserNotFoundException ex) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
      }
   }
   
   @DeleteMapping("/{id}")
   public void deleteUserById(@PathVariable long id) {
      userService.deleteUserById(id);
   }
   
   @GetMapping("/byusername/{username}")
   public User getUserByUsername(@PathVariable String username) throws UsernameNotFoundException {
      User user = userService.getUserByUsername(username);
      if (user == null) {
         throw new UsernameNotFoundException("Username: " + username + " not found in repository");
      } else {
         return user;
      }
   }
   
   @GetMapping("/bylastname/{lastname}")
   public List<User> getUsersByLastname(@PathVariable String lastname) {
      return userService.getUsersByLastname(lastname);
   }
}
