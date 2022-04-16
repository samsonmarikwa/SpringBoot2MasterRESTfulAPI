package com.samsonmarikwa.restservices.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
/* gives the name by which the class will be known in the database & in HQL / JPQL queries */
//@Entity(name = "Users")

/* gives the name by which the class will be known in the database. So in JPQL, you would
   have to use the class name or the @Entity name
*/
@Table(name = "USERS")
//@JsonIgnoreProperties({"firstname", "lastname"}) -- Static filtering JsonIgnore
@JsonFilter(value = "userFilter")
public class User extends RepresentationModel<User> {

   @Id
   @GeneratedValue
   private Long userId;
   
   @NotEmpty(message="Username is a mandatory field. Please provide username")
   @Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
   private String username;
   
   @Size(min=2, message="FirstName should have at least 2 characters")
   @Column(name = "FIRST_NAME", length = 50, nullable = false)
   
   private String firstname;
   @Column(name = "LAST_NAME", length = 50, nullable = false)
   
   private String lastname;
   @Column(name = "EMAIL_ADDRESS", length = 50, nullable = false)
   private String email;
   @Column(name = "ROLE", length = 50, nullable = false)
   private String role;
   
//   @JsonIgnore - Static filtering JsonIgnore
   @Column(name = "SSN", length = 50, nullable = false, unique = true)
   private String ssn;
   
   @OneToMany(mappedBy = "user")
   private List<Order> orders;
   
   public User() {
   }
   
   public User(Long userId, String username, String firstname, String lastname, String email, String role, String ssn) {
      this.userId = userId;
      this.username = username;
      this.firstname = firstname;
      this.lastname = lastname;
      this.email = email;
      this.role = role;
      this.ssn = ssn;
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
   
   @Override
   public String toString() {
      return "User[" +
            "userId=" + userId +
            ", username='" + username + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", email='" + email + '\'' +
            ", role='" + role + '\'' +
            ", ssn='" + ssn + '\'' +
            ", orders=" + orders +
            ']';
   }
}
