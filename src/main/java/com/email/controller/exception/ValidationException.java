package com.email.controller.exception;

/** @author Sonu Kumar, created on 20-Jan-2019 */
public class ValidationException extends Throwable {
  private Integer code;

  public ValidationException(String message) {
    super(message);
  }

  public ValidationException(String message, int code) {
    super(message);
    this.code = code;
  }

  public ValidationException() {
    super();
  }

  public ValidationException(String message, Throwable cause) {
    super(message, cause);
  }

  public ValidationException(Throwable cause) {
    super(cause);
  }

  @Override
  public String getMessage() {
    if (this.code != null) {
      return super.getMessage() + code;
    }
    return super.getMessage();
  }
}
