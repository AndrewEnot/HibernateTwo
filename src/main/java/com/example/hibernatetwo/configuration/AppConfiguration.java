package com.example.hibernatetwo.configuration;

/*
 * @author Oksiuta Andrii
 * 17.01.2023
 * 13:40
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}
