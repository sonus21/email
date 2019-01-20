package com.email.repository.mysql.model;

import com.email.repository.mysql.converter.SetToStringConverter;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** @author Sonu Kumar, created on 20-Jan-2019 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
    name = "config",
    uniqueConstraints = {@UniqueConstraint(name = "email_id_unique", columnNames = "email_id")})
public class Email extends AbstractLongIdEntity {
  @Column(name = "email_id", nullable = false)
  private String emailId;

  @Column(name = "sender", nullable = false)
  @Convert(converter = SetToStringConverter.class)
  private Set<Long> to;

  @Column(name = "recipient", nullable = false)
  private Long from;

  @Column(name = "subject", length = 1028, nullable = false)
  private String subject;

  @Column(name = "body", columnDefinition = "TEXT", nullable = false)
  private String body;

  @Column(name = "parent_id")
  private Long parentMailId;
}
