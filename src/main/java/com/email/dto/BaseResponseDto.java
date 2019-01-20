package com.email.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** @author Sonu Kumar, created on 20-Jan-2019 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseResponseDto implements Serializable {
  private int code;
  private String message;
}
