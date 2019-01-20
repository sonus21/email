package com.email.service;

import com.email.controller.exception.ValidationException;
import com.email.dto.BaseResponseDto;
import com.email.dto.EmailIdResponseDto;
import com.email.dto.SendEmailDto;

/** @author Sonu Kumar, created on 20-Jan-2019 */
public interface EmailService {

  BaseResponseDto sendEmail(Long userId, SendEmailDto sendEmailDto) throws ValidationException;

  EmailIdResponseDto createEmailEntry(Long userId, SendEmailDto sendEmailDto)
      throws ValidationException;

  BaseResponseDto deleteEmail(Long userId, String emailId) throws ValidationException;

  BaseResponseDto reply(Long userId, SendEmailDto sendEmailDto) throws ValidationException;
}
