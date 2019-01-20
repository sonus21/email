package com.email.repository.mysql.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/** @author Sonu Kumar, created on 20-Jan-2019 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum MailType {
  DRAFT(1),
  REPLY(3),
  RESPONSE(4),
  COMPOSE(5);
  private Integer code;

  public static MailType getByCode(Integer code) {
    for (MailType mailType : values()) {
      if (mailType.code.equals(code)) {
        return mailType;
      }
    }
    throw new IllegalArgumentException("Invalid code " + code);
  }
}
