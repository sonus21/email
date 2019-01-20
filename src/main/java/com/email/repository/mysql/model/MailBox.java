package com.email.repository.mysql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** @author Sonu Kumar, created on 20-Jan-2019 */
@Entity
@Table(
    name = "mail_box",
    indexes = {
      @Index(name = "email_idx", columnList = "email_id"),
      @Index(name = "parent_email_idx", columnList = "parent_email_id"),
      @Index(name = "mail_type_idx", columnList = "type"),
      @Index(name = "user_idx", columnList = "user_id")
    },
    uniqueConstraints = {
      @UniqueConstraint(
          name = "email_id_and_user_id",
          columnNames = {"user_id", "email_id"})
    })
@Getter
@Setter
@NoArgsConstructor
public class MailBox extends AbstractLongIdEntity {
  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = "email_id", nullable = false)
  private Long emailId;

  @Column(name = "parent_email_id")
  private Long parentEmailId;

  @Column(name = "type", nullable = false)
  private MailType mailType;
}
