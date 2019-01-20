package com.email.controller.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/** @author Sonu Kumar, created on 20-Jan-2019 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ValidationErrorCode {
  INVALID_EMAIL_ID(1),
  INVALID_PARENT_EMAIL_ID(2),
  ONE_MORE_EMAILS_ARE_NOT_VALID(3),
  EMPTY_RECIPIENTS(4),
  EMPTY_BODY(5),
  EMPTY_SUBJECT(6);
  private Integer code;

  public ValidationException asValidationException() {
    return new ValidationException(name(), getCode());
  }
}
