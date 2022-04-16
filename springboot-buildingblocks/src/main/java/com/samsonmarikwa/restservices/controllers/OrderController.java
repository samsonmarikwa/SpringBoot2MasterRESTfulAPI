package com.samsonmarikwa.restservices.controllers;

import com.samsonmarikwa.restservices.entities.Order;
import com.samsonmarikwa.restservices.entities.User;
import com.samsonmarikwa.restservices.exceptions.OrderNotFoundException;
import com.samsonmarikwa.restservices.exceptions.UserNotFoundException;
import com.samsonmarikwa.restservices.repositories.OrderRepository;
import com.samsonmarikwa.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value="/users")
public class OrderController {
   @Autowired
   private UserRepository userRepository;
   
   @Autowired
   private OrderRepository orderRepository;
   
   // Get all orders for a user
   @GetMapping("/{userId}/orders")
   public List<Order> getAllOrders(@PathVariable @Min(1) Long userId) throws UserNotFoundException {
      Optional<User> userOptional = userRepository.findById(userId);
      if (!userOptional.isPresent()) {
         throw new UserNotFoundException("User Not Found");
      }
      return userOptional.get().getOrders();
   }
   
   @PostMapping("{userId}/orders")
   public Order createOrder(@PathVariable Long userId, @RequestBody Order order) throws UserNotFoundException {
      Optional<User> userOptional = userRepository.findById(userId);
      if (!userOptional.isPresent()) {
         throw new UserNotFoundException("User Not Found");
      }
      
      User user = userOptional.get();
      order.setUser(user);
      return orderRepository.save(order);
   }
   
   @GetMapping("{userId}/orders/{orderId}")
   public Order getOrderByOrderId(@PathVariable Long userId, @PathVariable Long orderId)
         throws UserNotFoundException, OrderNotFoundException {
      Optional<User> userOptional = userRepository.findById(userId);
      if (!userOptional.isPresent()) {
         throw new UserNotFoundException("User Not Found");
      }
      
      Optional<Order> optionalOrder = orderRepository.findById(orderId);
      if (!optionalOrder.isPresent()) {
         throw new OrderNotFoundException("Order Not Found");
      }
      
      Order order = optionalOrder.get();
      order.setUser(userOptional.get());
      return order;
   }
}
