package com.samsonmarikwa.restservices.dtos;

import com.samsonmarikwa.restservices.entities.Order;

import java.util.List;

public class UserMmDto {
   private Long userId;
   private String username;
   private String firstname;
   private List<Order> orders;
   
   public Long getUserId() {
      return userId;
   }
   
   public void setUserId(Long userId) {
      this.userId = userId;
   }
   
   public String getUsername() {
      return username;
   }
   
   public void setUsername(String username) {
      this.username = username;
   }
   
   public String getFirstname() {
      return firstname;
   }
   
   public void setFirstname(String firstname) {
      this.firstname = firstname;
   }
   
   public List<Order> getOrders() {
      return orders;
   }
   
   public void setOrders(List<Order> orders) {
      this.orders = orders;
   }
}
