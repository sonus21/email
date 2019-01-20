package com.email.repository.mysql.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/** @author Sonu Kumar, created on 20-Jan-2019 */
@MappedSuperclass
@Getter
@Setter
public abstract class AbstractIntegerIdEntity extends AbstractCreateDeleteUpdateEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;
}
