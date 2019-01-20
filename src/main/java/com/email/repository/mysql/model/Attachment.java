package com.email.repository.mysql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** @author Sonu Kumar, created on 20-Jan-2019 */
@Entity
@Table(
    name = "attachment",
    uniqueConstraints = {@UniqueConstraint(name = "email_id", columnNames = "email_id")})
@Getter
@Setter
@NoArgsConstructor
public class Attachment extends AbstractLongIdEntity {

  @Column(name = "email_id", nullable = false)
  private Long emailId;

  @Column(name = "file_url", nullable = false)
  private String filePath;

  @Column(name = "file_type", nullable = false)
  private String fileType;

  @Column(name = "attachment_order", nullable = false)
  private Short attachmentOrder = 0;
}
