package com.email.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** @author Sonu Kumar, created on 20-Jan-2019 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendEmailDto extends BaseRequestDto {
  private String emailId;
  private Set<String> to;
  private String subject;
  private String body;
  private String parentEmailId;
}
