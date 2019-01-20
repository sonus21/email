package com.email.service.impl;

import com.email.controller.exception.ValidationErrorCode;
import com.email.controller.exception.ValidationException;
import com.email.dto.BaseResponseDto;
import com.email.dto.EmailIdResponseDto;
import com.email.dto.SendEmailDto;
import com.email.repository.mysql.model.Email;
import com.email.repository.mysql.model.MailBox;
import com.email.repository.mysql.model.MailType;
import com.email.repository.mysql.repository.EmailMySqlRepository;
import com.email.repository.mysql.repository.MailBoxMySqlRepository;
import com.email.repository.mysql.repository.UserMySqlRepository;
import com.email.service.EmailService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/** @author Sonu Kumar, created on 20-Jan-2019 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class EmailServiceImpl implements EmailService {
  private EmailMySqlRepository emailMySqlRepository;
  private UserMySqlRepository userMySqlRepository;
  private MailBoxMySqlRepository mailBoxMySqlRepository;

  @Override
  @Transactional(rollbackFor = {Exception.class})
  public BaseResponseDto sendEmail(Long userId, SendEmailDto sendEmailDto)
      throws ValidationException {
    if (sendEmailDto.getEmailId() == null) {
      throw ValidationErrorCode.INVALID_EMAIL_ID.asValidationException();
    }
    if (sendEmailDto.getTo() == null) {
      throw ValidationErrorCode.EMPTY_RECIPIENTS.asValidationException();
    }
    if (!StringUtils.hasText(sendEmailDto.getBody())) {
      throw ValidationErrorCode.EMPTY_BODY.asValidationException();
    }

    Optional<Email> optionalEmail = emailMySqlRepository.findByEmailId(sendEmailDto.getEmailId());
    if (!optionalEmail.isPresent()) {
      throw ValidationErrorCode.INVALID_EMAIL_ID.asValidationException();
    }

    Email email = optionalEmail.get();

    Optional<MailBox> optionalMailBox =
        mailBoxMySqlRepository.findByEmailIdAndUserId(email.getId(), userId);
    if (!optionalMailBox.isPresent()) {
      throw ValidationErrorCode.INVALID_EMAIL_ID.asValidationException();
    }
    MailBox senderMailBox = optionalMailBox.get();
    Long parentEmailId = null;
    if (sendEmailDto.getParentEmailId() != null) {
      Optional<Email> optionalParentEmail =
          emailMySqlRepository.findByEmailId(sendEmailDto.getParentEmailId());
      if (!optionalParentEmail.isPresent()) {
        throw ValidationErrorCode.INVALID_PARENT_EMAIL_ID.asValidationException();
      }
      parentEmailId = optionalParentEmail.get().getId();
    } else if (!StringUtils.hasText(sendEmailDto.getSubject())) {
      throw ValidationErrorCode.EMPTY_SUBJECT.asValidationException();
    }

    Set<Long> to = getUserIdFromEmailId(sendEmailDto.getTo());
    email.setSubject(sendEmailDto.getSubject());
    email.setBody(sendEmailDto.getBody());
    email.setFrom(userId);
    email.setTo(to);
    email.setParentMailId(parentEmailId);
    senderMailBox.setParentEmailId(parentEmailId);
    emailMySqlRepository.save(email);

    senderMailBox.setEmailId(email.getId());
    if (parentEmailId != null) {
      senderMailBox.setMailType(MailType.COMPOSE);
    } else {
      senderMailBox.setMailType(MailType.REPLY);
    }
    List<MailBox> mailBoxList = new ArrayList<>();
    mailBoxList.add(senderMailBox);
    for (Long recipientId : to) {
      MailBox mailBox = new MailBox();
      mailBox.setUserId(recipientId);
      mailBox.setEmailId(email.getId());
      mailBox.setMailType(MailType.RESPONSE);
      mailBox.setParentEmailId(parentEmailId);
      mailBoxList.add(mailBox);
    }
    mailBoxMySqlRepository.saveAll(mailBoxList);

    return new BaseResponseDto();
  }

  private Set<Long> getUserIdFromEmailId(Set<String> emailIds) throws ValidationException {
    Map<String, Long> emailIdToUserIdMap = userMySqlRepository.getEmailIdToUserIdMap(emailIds);
    if (emailIdToUserIdMap.size() != emailIds.size()) {
      throw ValidationErrorCode.ONE_MORE_EMAILS_ARE_NOT_VALID.asValidationException();
    }
    return new HashSet<>(emailIdToUserIdMap.values());
  }

  @Override
  @Transactional(rollbackFor = {Exception.class})
  public EmailIdResponseDto createEmailEntry(Long userId, SendEmailDto sendEmailDto)
      throws ValidationException {
    Email email = null;
    if (sendEmailDto.getEmailId() != null) {
      Optional<Email> optionalEmail = emailMySqlRepository.findByEmailId(sendEmailDto.getEmailId());
      if (!optionalEmail.isPresent()) {
        throw ValidationErrorCode.INVALID_EMAIL_ID.asValidationException();
      }
      email = optionalEmail.get();
    }
    if (email == null) {
      email = new Email();
      email.setEmailId(UUID.randomUUID().toString());
    }
    Set<String> recipients = sendEmailDto.getTo();
    if (!CollectionUtils.isEmpty(recipients)) {
      email.setTo(getUserIdFromEmailId(recipients));
    }

    if (sendEmailDto.getParentEmailId() != null) {
      Optional<Email> optionalEmail =
          emailMySqlRepository.findByEmailId(sendEmailDto.getParentEmailId());
      if (!optionalEmail.isPresent()) {
        throw ValidationErrorCode.INVALID_PARENT_EMAIL_ID.asValidationException();
      }
      email.setParentMailId(optionalEmail.get().getId());
    }
    email.setBody(sendEmailDto.getBody());
    email.setFrom(userId);
    email.setSubject(sendEmailDto.getSubject());
    email.setIsActive(true);
    email.setUpdatedOn(System.currentTimeMillis());
    email = emailMySqlRepository.save(email);
    Optional<MailBox> optionalMailBox =
        mailBoxMySqlRepository.findByEmailIdAndUserId(email.getId(), userId);
    MailBox mailBox = optionalMailBox.orElseGet(MailBox::new);
    mailBox.setEmailId(email.getId());
    mailBox.setMailType(MailType.DRAFT);
    mailBox.setUserId(userId);
    mailBoxMySqlRepository.save(mailBox);
    return new EmailIdResponseDto(email.getEmailId());
  }

  @Override
  @Transactional(rollbackFor = {Exception.class})
  public BaseResponseDto deleteEmail(Long userId, String emailId) throws ValidationException {
    Optional<Email> optionalEmail = emailMySqlRepository.findByEmailId(emailId);
    if (!optionalEmail.isPresent()) {
      throw new ValidationException("Invalid config id");
    }
    Optional<MailBox> mailBoxOptional =
        mailBoxMySqlRepository.findByEmailIdAndUserId(optionalEmail.get().getId(), userId);
    if (!mailBoxOptional.isPresent()) {
      throw new ValidationException("Invalid config id");
    }
    MailBox mailBox = mailBoxOptional.get();
    mailBox.setIsActive(false);
    mailBoxMySqlRepository.save(mailBox);
    return new BaseResponseDto();
  }

  @Override
  @Transactional(rollbackFor = {Exception.class})
  public BaseResponseDto reply(Long userId, SendEmailDto sendEmailDto) throws ValidationException {
    return sendEmail(userId, sendEmailDto);
  }
}
