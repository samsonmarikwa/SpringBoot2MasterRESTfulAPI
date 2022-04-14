package com.samsonmarikwa.restservices.controllers;

import com.samsonmarikwa.restservices.entities.User;
import com.samsonmarikwa.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
   
   @Autowired
   private UserService userService;
   
   @GetMapping("/users")
   public List<User> getAllUsers() {
      return userService.getAllUsers();
   }
   
   @PostMapping("/users")
   public User createUser(@RequestBody User user) {
      return userService.createUser(user);
   }
   
   @GetMapping("/users/{id}")
   public Optional<User> getUserById(@PathVariable long id) {
      return userService.getUserById(id);
   }
   
   @PutMapping("/users/{id}")
   public User updateUserById(@PathVariable long id, @RequestBody User user) {
      return userService.updateUserById(id, user);
   }
   
   @DeleteMapping("/users/{id}")
   public void deleteUserById(@PathVariable long id) {
      userService.deleteUserById(id);
   }
   
   @GetMapping("/users/byusername/{username}")
   public User getUserByUsername(@PathVariable String username) {
      return userService.getUserByUsername(username);
   }
   
   @GetMapping("/users/bylastname/{lastname}")
   public List<User> getUsersByLastname(@PathVariable String lastname) {
      return userService.getUsersByLastname(lastname);
   }
}
