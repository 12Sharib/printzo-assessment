package com.project.product_management;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.project.product_management.config.JwtTokenProvider;
import com.project.product_management.model.entity.User;
import com.project.product_management.model.repository.UserRepository;
import com.project.product_management.service.constants.MessageConstants;
import com.project.product_management.service.dto.LoginRequestDto;
import com.project.product_management.service.dto.RegisterUserDto;
import com.project.product_management.service.exceptions.ProductManagementException;
import com.project.product_management.service.interfaces.UserService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;

@WithMockUser
class UserServiceImplTest extends TestBase {

  @MockBean
  private UserRepository userRepository;


  @MockBean
  private PasswordEncoder passwordEncoder;

  @MockBean
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private UserService userService;

  private User user;

  @BeforeEach
  void setUp() {
    user = new User();
    user.setId(1);
    user.setEmail("test@example.com");
    user.setPassword("password");

  }


  @Test
  void registerUserWhenEmailIsNew() throws ProductManagementException {
    RegisterUserDto dto = new RegisterUserDto("user", "new@example.com", "password", null);
    when(userRepository.existsByEmail(dto.getEmail())).thenReturn(false);
    when(passwordEncoder.encode(dto.getPassword())).thenReturn("hashedPassword");

    String result = userService.registerUser(dto);
    assertEquals(MessageConstants.USER_REGISTER_SUCCESS, result);
  }

  @Test
  void registerUserWhenEmailExists() {
    RegisterUserDto dto = new RegisterUserDto("user", "test@example.com", "password", null);
    when(userRepository.existsByEmail(dto.getEmail())).thenReturn(true);

    assertThrows(ProductManagementException.class, () -> userService.registerUser(dto));
  }

  @Test
  void loginUserWhenCredentialsAreValid() throws ProductManagementException {
    LoginRequestDto dto = new LoginRequestDto("test@example.com", "password");
    when(userRepository.findByEmail(dto.getEmail())).thenReturn(Optional.of(user));
    when(passwordEncoder.matches(dto.getPassword(), user.getPassword())).thenReturn(true);
    when(jwtTokenProvider.generateToken(anyString(), anyString())).thenReturn("validToken");

    String token = userService.loginUser(dto);
    assertEquals("validToken", token);
  }

  @Test
  void loginUserWhenUserNotFound() {
    LoginRequestDto dto = new LoginRequestDto("notfound@example.com", "password");
    when(userRepository.findByEmail(dto.getEmail())).thenReturn(Optional.empty());

    assertThrows(ProductManagementException.class, () -> userService.loginUser(dto));
  }

  @Test
  void loginUserWhenPasswordIsIncorrect() {
    LoginRequestDto dto = new LoginRequestDto("test@example.com", "wrongpassword");
    when(userRepository.findByEmail(dto.getEmail())).thenReturn(Optional.of(user));
    when(passwordEncoder.matches(dto.getPassword(), user.getPassword())).thenReturn(false);

    assertThrows(ProductManagementException.class, () -> userService.loginUser(dto));
  }
}
