package com.email.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** @author Sonu Kumar, created on 20-Jan-2019 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class EmailIdResponseDto extends BaseResponseDto {
  private String emailId;
}
