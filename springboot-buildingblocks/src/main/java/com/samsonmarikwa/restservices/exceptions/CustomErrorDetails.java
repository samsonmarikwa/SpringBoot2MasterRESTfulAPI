package com.samsonmarikwa.restservices.exceptions;

import java.util.Date;

// This class is used in Global Exception Handling in @ControllerAdvice class
public class CustomErrorDetails {
   
   private Date timestamp;
   private String message;
   private String errorDetails;
   
   public CustomErrorDetails(Date timestamp, String message, String errorDetails) {
      this.timestamp = timestamp;
      this.message = message;
      this.errorDetails = errorDetails;
   }
   
   public Date getTimestamp() {
      return timestamp;
   }
   
   public String getMessage() {
      return message;
   }
   
   public String getErrorDetails() {
      return errorDetails;
   }
}
