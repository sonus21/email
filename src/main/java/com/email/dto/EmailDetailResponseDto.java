package com.email.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** @author Sonu Kumar, created on 20-Jan-2019 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmailDetailResponseDto extends BaseResponseDto {
  private Long lastUpdatedDate;
  private List<EmailDetailDto> emails;
}
