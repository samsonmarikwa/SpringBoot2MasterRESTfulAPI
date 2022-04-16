package com.samsonmarikwa.restservices.services;

import com.samsonmarikwa.restservices.entities.User;
import com.samsonmarikwa.restservices.exceptions.UserExistsException;
import com.samsonmarikwa.restservices.exceptions.UserNotFoundException;
import com.samsonmarikwa.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
   
   public User createUser(User user) throws UserExistsException {
      User existingUser = userRepository.findUserByUsername(user.getUsername());
      
      if (existingUser != null) {
         throw new UserExistsException("User already exists in repository");
      }
      return userRepository.save(user);
   }
   
   public Optional<User> getUserById(long id) throws UserNotFoundException {
      Optional<User> user = userRepository.findById(id);
      if (!user.isPresent()) {
         throw new UserNotFoundException("User Not found in User Repository");
      }
      return user;
   }
   
   public User updateUserById(Long id, User user) throws UserNotFoundException {
      Optional<User> optionalUser = userRepository.findById(id);
      if (!optionalUser.isPresent()) {
         throw new UserNotFoundException("User Not found in User Repository, provide correct User Id");
      }
      
      user.setUserId(id);
      return userRepository.save(user);
   }
   
   public void deleteUserById(long id) {
      Optional<User> optionalUser = userRepository.findById(id);
      // Exception is thrown only in the service layer, no need to put exception handling in the controller.
      if (!optionalUser.isPresent()) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Not found in User Repository, provide correct User Id");
      }
      userRepository.deleteById(id);
   }
   
   public User getUserByUsername(String username) {
      return userRepository.findUserByUsername(username);
   }
   
   public List<User> getUsersByLastname(String lastname) {
      return userRepository.findUserByLastname(lastname);
   }
}
