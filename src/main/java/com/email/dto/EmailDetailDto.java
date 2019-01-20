package com.email.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** @author Sonu Kumar, created on 20-Jan-2019 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDetailDto extends BaseRequestDto {
  private Long createdOn;
  private String senderEmail;
  private String senderName;
  private String subject;
  private String body;
  private String mailType;
  private String emailId;
  private List<AttachmentDto> attachments;
}
