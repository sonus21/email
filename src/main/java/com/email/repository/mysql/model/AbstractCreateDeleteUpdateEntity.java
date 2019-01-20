package com.email.repository.mysql.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/** @author Sonu Kumar, created on 20-Jan-2019 */
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractCreateDeleteUpdateEntity {


  @Column(name = "created_on")
  @NotNull
  protected Long createdOn;

  @Column(name = "updated_on")
  @NotNull
  protected Long updatedOn;

  @Column(name = "deleted_on")
  protected Long deletedOn;

  @Column(name = "is_active")
  @NotNull
  protected Boolean isActive;

  @PrePersist
  public void beforeSave() {
    this.updatedOn = this.createdOn = System.currentTimeMillis();
    this.isActive = Boolean.TRUE;
  }

  @PreUpdate
  public void beforeUpdate() {
    this.updatedOn = System.currentTimeMillis();
  }
}
