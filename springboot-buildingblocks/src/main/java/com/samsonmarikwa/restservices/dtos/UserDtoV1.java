package com.samsonmarikwa.restservices.dtos;

import com.fasterxml.jackson.annotation.JsonView;
import com.samsonmarikwa.restservices.entities.Order;
import com.samsonmarikwa.restservices.entities.Views;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class UserDtoV1 {
   
   private Long userId;
   private String username;
   private String firstname;
   private String lastname;
   private String email;
   private String role;
   private String ssn;
   private List<Order> orders;
   
   public UserDtoV1() {
   }
   
   public UserDtoV1(Long userId, String username, String firstname, String lastname, String email, String role, String ssn, List<Order> orders) {
      this.userId = userId;
      this.username = username;
      this.firstname = firstname;
      this.lastname = lastname;
      this.email = email;
      this.role = role;
      this.ssn = ssn;
      this.orders = orders;
   }
   
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
   
   public String getLastname() {
      return lastname;
   }
   
   public void setLastname(String lastname) {
      this.lastname = lastname;
   }
   
   public String getEmail() {
      return email;
   }
   
   public void setEmail(String email) {
      this.email = email;
   }
   
   public String getRole() {
      return role;
   }
   
   public void setRole(String role) {
      this.role = role;
   }
   
   public String getSsn() {
      return ssn;
   }
   
   public void setSsn(String ssn) {
      this.ssn = ssn;
   }
   
   public List<Order> getOrders() {
      return orders;
   }
   
   public void setOrders(List<Order> orders) {
      this.orders = orders;
   }
}
