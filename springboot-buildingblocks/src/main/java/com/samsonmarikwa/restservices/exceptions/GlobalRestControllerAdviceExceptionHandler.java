package com.samsonmarikwa.restservices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

//@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {
   
   @ExceptionHandler(UsernameNotFoundException.class)
   @ResponseStatus(HttpStatus.NOT_FOUND)
   public CustomErrorDetails usernameNotFoundException(UsernameNotFoundException ex) {
      return new CustomErrorDetails(
            new Date(), "From @RestControllerAdvice - Username not found", ex.getMessage());
   }
}
