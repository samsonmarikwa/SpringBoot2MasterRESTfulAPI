package com.samsonmarikwa.restservices.services;

import com.samsonmarikwa.restservices.entities.User;
import com.samsonmarikwa.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
   
   @Autowired
   private UserRepository userRepository;
   
   // getAllUsers
   public List<User> getAllUsers() {
      return userRepository.findAll();
   }
   
   public User createUser(User user) {
      return userRepository.save(user);
   }
   
   public Optional<User> getUserById(long id) {
      return userRepository.findById(id);
   }
   
   public User updateUserById(Long id, User user) {
      user.setId(id);
      return userRepository.save(user);
   }
   
   public void deleteUserById(long id) {
      if (userRepository.findById(id).isPresent()) {
         userRepository.deleteById(id);
      }
   }
   
   public User getUserByUsername(String username) {
      return userRepository.findUserByUsername(username);
   }
   
   public List<User> getUsersByLastname(String lastname) {
      return userRepository.findUserByLastname(lastname);
   }
}
