package com.samsonmarikwa.restservices.controllers;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.samsonmarikwa.restservices.entities.User;
import com.samsonmarikwa.restservices.exceptions.UserNotFoundException;
import com.samsonmarikwa.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value="/jacksonfilter/users")
@Validated
public class UserMappingJacksonController {
   
   @Autowired
   private UserService userService;
   
   // getUserById - fields with HashSet
   @GetMapping("/{id}")
   public MappingJacksonValue getUserById(@PathVariable @Min(1) long id) {  // @Min requires @Validated annotation at class level
      try {
         Optional<User> userOptional = userService.getUserById(id);
         User user = userOptional.get();
   
         Set<String> fields = new HashSet<>();
         fields.add("userId");
         fields.add("username");
         fields.add("ssn");
         fields.add("orders");
         
         FilterProvider filterProvider = new SimpleFilterProvider()
               .addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
         
         MappingJacksonValue mapper = new MappingJacksonValue(user);
         
         mapper.setFilters(filterProvider);
         return mapper;
         
      } catch (UserNotFoundException ex) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
      }
   }
   
   // getUserById - fields with @RequestParam
   @GetMapping("/params/{id}")
   public MappingJacksonValue getUserById2(@PathVariable @Min(1) long id, @RequestParam Set<String> fields) {
      try {
         Optional<User> userOptional = userService.getUserById(id);
         User user = userOptional.get();
         
         FilterProvider filterProvider = new SimpleFilterProvider()
               .addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
   
         MappingJacksonValue mapper = new MappingJacksonValue(user);
   
         mapper.setFilters(filterProvider);
         return mapper;
   
      } catch (UserNotFoundException ex) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
      }
   }
   
}
