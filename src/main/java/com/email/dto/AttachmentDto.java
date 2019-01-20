package com.email.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** @author Sonu Kumar, created on 20-Jan-2019 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentDto extends BaseRequestDto {
  @NotNull private String filePath;
  @NotNull private String fileType;
  @NotNull private Short attachmentOrder;
}
