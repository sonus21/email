package com.email.controller;

import static com.email.constant.HeaderConstant.USER_ID;
import static com.email.constant.PathVariableConstants.EMAIL_ID;

import com.email.controller.exception.ValidationException;
import com.email.dto.BaseResponseDto;
import com.email.dto.EmailIdResponseDto;
import com.email.dto.SendEmailDto;
import com.email.service.EmailService;
import io.micrometer.core.annotation.Timed;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/** @author Sonu Kumar, created on 20-Jan-2019 */
@RestController("/api/v1/email")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailController {
  private EmailService emailService;

  @PostMapping("send")
  @ResponseBody
  @Timed
  public BaseResponseDto sendEmail(
      @RequestHeader(USER_ID) Long userId, @Valid @NotNull SendEmailDto sendEmailDto)
      throws ValidationException {
    return emailService.sendEmail(userId, sendEmailDto);
  }

  @PostMapping("create")
  @ResponseBody
  @Timed
  public EmailIdResponseDto createEmailEntry(
      @RequestHeader(USER_ID) Long userId, @Valid @NotNull SendEmailDto sendEmailDto)
      throws ValidationException {
    return emailService.createEmailEntry(userId, sendEmailDto);
  }

  @PostMapping("reply")
  @ResponseBody
  @Timed
  public BaseResponseDto reply(
      @RequestHeader(USER_ID) Long userId, @Valid @NotNull SendEmailDto sendEmailDto)
      throws ValidationException {
    return emailService.reply(userId, sendEmailDto);
  }

  @DeleteMapping("{email-id}")
  @ResponseBody
  @Timed
  public BaseResponseDto deleteEmail(
      @RequestHeader(USER_ID) Long userId, @PathVariable(EMAIL_ID) String emailId)
      throws ValidationException {
    return emailService.deleteEmail(userId, emailId);
  }
}
