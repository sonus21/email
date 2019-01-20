package com.email.controller;

import com.email.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/** @author Sonu Kumar, created on 20-Jan-2019 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
  private UserService userService;
}
