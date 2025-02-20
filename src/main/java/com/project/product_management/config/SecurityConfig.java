package com.project.product_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private static final String[] AUTH_WHITELIST = {
      "/v3/api-docs/**",
      "/swagger-ui/**",
      "/swagger-resources/**",
      "/user/register/**",
      "/user/login/**",
      "/h2-console/**",
      "/actuator/**"
  };
  private final String ADMIN = "ROLE_ADMIN";
  private final String USER = "ROLE_USER";

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
  }


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .headers(headers -> headers.frameOptions().disable())
        .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
//            .requestMatchers(AUTH_WHITELIST).permitAll()
//            .requestMatchers( "/product/**").hasAuthority(USER)
//            .requestMatchers( "/admin/**").hasAuthority(ADMIN)
//            .requestMatchers( "/type/**").hasAuthority(ADMIN)
//           .anyRequest().authenticated()

        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}