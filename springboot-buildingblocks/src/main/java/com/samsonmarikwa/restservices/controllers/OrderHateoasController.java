package com.samsonmarikwa.restservices.controllers;

import com.samsonmarikwa.restservices.entities.Order;
import com.samsonmarikwa.restservices.entities.User;
import com.samsonmarikwa.restservices.exceptions.UserNotFoundException;
import com.samsonmarikwa.restservices.repositories.OrderRepository;
import com.samsonmarikwa.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value="/hateoas/users")
public class OrderHateoasController {
   
   @Autowired
   private UserRepository userRepository;
   
   @Autowired
   private OrderRepository orderRepository;
   
   // Get all orders for a user
   @GetMapping("/{userId}/orders")
   public CollectionModel<Order> getAllOrders(@PathVariable @Min(1) Long userId) throws UserNotFoundException {
      Optional<User> userOptional = userRepository.findById(userId);
      if (!userOptional.isPresent()) {
         throw new UserNotFoundException("User Not Found");
      }
      List<Order> orders = userOptional.get().getOrders();
   
      CollectionModel<Order> orderCollectionModel = CollectionModel.of(orders);
      return orderCollectionModel;
   }
   
}
