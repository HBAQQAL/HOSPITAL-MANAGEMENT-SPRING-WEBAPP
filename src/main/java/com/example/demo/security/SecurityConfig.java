package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

  private PasswordEncoder passwordEncoder;

  @Bean
  public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
    PasswordEncoder passwordEncoder = passwordEncoder();
    return new InMemoryUserDetailsManager(
        User.withUsername("hamza").password(passwordEncoder.encode("hamza")).roles("USER", "ADMIN").build(),
        User.withUsername("user").password(passwordEncoder.encode("user")).roles("USER").build(),
        User.withUsername("user2").password("user2").roles("USER").build());
  }

  @SuppressWarnings("removal")
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    httpSecurity.formLogin().loginPage("/login").permitAll();
    httpSecurity.authorizeHttpRequests().requestMatchers("/images/logo.jpg").permitAll();
    httpSecurity.authorizeHttpRequests().requestMatchers("/webjars/**").permitAll();
    httpSecurity.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER");
    httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");
    httpSecurity.authorizeHttpRequests().anyRequest().authenticated();

    return httpSecurity.build();

  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
