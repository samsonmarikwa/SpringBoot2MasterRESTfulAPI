package com.samsonmarikwa.restservices.controllers;

import com.samsonmarikwa.restservices.entities.Order;
import com.samsonmarikwa.restservices.entities.User;
import com.samsonmarikwa.restservices.exceptions.UserNotFoundException;
import com.samsonmarikwa.restservices.repositories.UserRepository;
import com.samsonmarikwa.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController {
   
   @Autowired
   private UserRepository userRepository;
   
   @Autowired
   private UserService userService;
   
   @GetMapping
   public CollectionModel<User> getAllUsers() throws UserNotFoundException {
      List<User> users = userService.getAllUsers();
   
      for(User user : users) {
         // Self link
         Link selflink = linkTo(this.getClass()).slash(user.getUserId()).withSelfRel();
         user.add(selflink);
         
         // Relationship link with getAllOrders
         CollectionModel<Order> ordersModelCollection = methodOn(OrderHateoasController.class).getAllOrders(user.getUserId());
         Link ordersLink = linkTo(ordersModelCollection).withRel("all-orders");
         user.add(ordersLink);
      }
      
      // Self link for getAllUsers
      Link link = linkTo(this.getClass()).withSelfRel();
      CollectionModel<User> userCollectionModel = CollectionModel.of(users, link);
      return userCollectionModel;
   }
   
   @GetMapping("/{id}")
   public EntityModel<User> getUserById(@PathVariable @Min(1) long id) {  // @Min requires @Validated annotation at class level
      try {
         Optional<User> optionalUser = userService.getUserById(id);
         User user = optionalUser.get();
         Link selflink = linkTo(this.getClass()).slash(user.getUserId()).withSelfRel();
         user.add(selflink);
         EntityModel<User> userEntityModel = EntityModel.of(user);
         return userEntityModel;
         
      } catch (UserNotFoundException ex) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
      }
   }
}
