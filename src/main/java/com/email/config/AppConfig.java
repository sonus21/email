package com.email.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/** @author Sonu Kumar, created on 20-Jan-2019 */
@Configuration
@ComponentScan(
    value = {
        "com.email.service",
        "com.email.controller",
        "com.email.repository",
    })
@EnableJpaRepositories(value = "com.email.repository.mysql.repository")
@EntityScan("com.config.repository.mysql.model")
@EnableRedisRepositories(value = "com.email.repository.redis")
public class AppConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
