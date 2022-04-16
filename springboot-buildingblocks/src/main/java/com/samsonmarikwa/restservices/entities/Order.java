package com.samsonmarikwa.restservices.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order extends RepresentationModel<Order> {
   @Id
   @GeneratedValue
   private Long orderid;
   private String orderdescription;
   
   @ManyToOne(fetch = FetchType.LAZY)  // will be loaded in memory only when it is requested through a getUser()
   @JsonIgnore
   private User user;
   
   public Order() {
   }
   
   public Order(Long orderid, String orderdescription, User user) {
      this.orderid = orderid;
      this.orderdescription = orderdescription;
      this.user = user;
   }
   
   public Long getOrderid() {
      return orderid;
   }
   
   public void setOrderid(Long orderid) {
      this.orderid = orderid;
   }
   
   public String getOrderdescription() {
      return orderdescription;
   }
   
   public void setOrderdescription(String orderdescription) {
      this.orderdescription = orderdescription;
   }
   
   public User getUser() {
      return user;
   }
   
   public void setUser(User user) {
      this.user = user;
   }
   
}
