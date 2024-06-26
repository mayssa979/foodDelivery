package com.alibou.security.user;

import com.alibou.security.command.Command;
import com.alibou.security.token.Token;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "_user")
public class User implements UserDetails {

  @Id
  @GeneratedValue
  private Integer id;
  private String firstname;
  private String lastname;
  private String email;
  private String password;
  private String address;
  private long phoneNumber;

  @Enumerated(EnumType.STRING)
  private Role role;
  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private List<Token> tokens;


  @JsonIgnore
  @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
  private List<Command> commands;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String toString() {
    return "user{id=" + id + ", firstname='" + firstname + "', lastname=" + lastname + ", email='"+email+"}";
  }

}
