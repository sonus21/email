package com.email.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** @author Sonu Kumar, created on 20-Jan-2019 */
@ComponentScan("com.email.controller")
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {}
