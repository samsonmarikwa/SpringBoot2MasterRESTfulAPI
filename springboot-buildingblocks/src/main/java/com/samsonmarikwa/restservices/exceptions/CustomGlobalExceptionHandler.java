package com.samsonmarikwa.restservices.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice // class will be a global exception handler for all the controllers
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
   
   // MethodArgumentNotValidException
   @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(
         MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
      CustomErrorDetails customErrorDetails = new CustomErrorDetails(
            new Date(), "From MethodArgumentNotValid Exception in GEH", ex.getMessage());
      return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
//      return super.handleMethodArgumentNotValid(ex, headers, status, request);
   }
   
   // HttpRequestMethodNotSupported
   @Override
   protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
         HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
      CustomErrorDetails customErrorDetails = new CustomErrorDetails(
            new Date(), "From HttpRequestMethodNotSupported Exception in GEH - Method Not Allowed", ex.getMessage());
      return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
   }
   
   // UsernameNotFoundException
   @ExceptionHandler(UsernameNotFoundException.class) // in the above methods, it's not necessary because the parent methods have the annotation
   public final ResponseEntity<Object> handleUsernameNotFoundException(
         UsernameNotFoundException ex, WebRequest request) {
      CustomErrorDetails customErrorDetails = new CustomErrorDetails(
            new Date(), ex.getMessage(), request.getDescription(false));
      return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
   }
   
   // ConstraintViolationException // check in the log to determine the exception to handle
   @ExceptionHandler(ConstraintViolationException.class)
   public final ResponseEntity<Object> handleConstraintViolationException(
         ConstraintViolationException ex, WebRequest request) {
      CustomErrorDetails customErrorDetails = new CustomErrorDetails(
            new Date(), ex.getMessage(), request.getDescription(false));
      return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
   }
}
