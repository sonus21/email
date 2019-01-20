package com.email.repository.mysql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** @author Sonu Kumar, created on 20-Jan-2019 */
@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractLongIdEntity {
  @Column(name = "config", nullable = false)
  private String email;

  @Column(name = "first_name", nullable = false, length = 128)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 128)
  private String lastName;

  @Column(name = "password", nullable = false)
  private String password;
}
