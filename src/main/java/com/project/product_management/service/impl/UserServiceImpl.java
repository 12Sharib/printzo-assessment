package com.project.product_management.service.impl;


import com.project.product_management.config.JwtTokenProvider;
import com.project.product_management.controller.enums.ErrorEnum;
import com.project.product_management.controller.response.ErrorResponse;
import com.project.product_management.model.entity.User;
import com.project.product_management.model.repository.UserRepository;
import com.project.product_management.service.constants.MessageConstants;
import com.project.product_management.service.dto.LoginRequestDto;
import com.project.product_management.service.dto.RegisterUserDto;
import com.project.product_management.service.exceptions.ProductManagementException;
import com.project.product_management.service.interfaces.UserService;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

  public UserServiceImpl(UserRepository userRepository,
      PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtTokenProvider = jwtTokenProvider;
  }


  @Override
  public String registerUser(final RegisterUserDto registerUserDto)
      throws ProductManagementException {
    log.debug("Entry inside @class UserServiceImpl @method registerUser");

    validateEmail(registerUserDto.getEmail());

    final User user = new User();
    user.setUsername(registerUserDto.getUsername());
    user.setEmail(registerUserDto.getEmail());
    user.setRole(registerUserDto.getRole());
    user.setActive(true);
    user.setCreatedAt(LocalDateTime.now());
    user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));

    userRepository.save(user);

    return MessageConstants.USER_REGISTER_SUCCESS;
  }

  @Override
  public String loginUser(final LoginRequestDto loginRequestDto) throws ProductManagementException {
    final Optional<User> user = userRepository.findByEmail(loginRequestDto.getEmail());

    if (user.isEmpty()) {
      throw new ProductManagementException(new ErrorResponse(ErrorEnum.USER_NOT_FOUND, false));
    }

    if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.get().getPassword())) {
      throw new ProductManagementException(new ErrorResponse(ErrorEnum.INVALID_CREDENTIALS, false));
    }

    return jwtTokenProvider.generateToken(user.get().getEmail(),
        String.valueOf(user.get().getRole()));
  }


  private void validateEmail(final String email) throws ProductManagementException {
    if (userRepository.existsByEmail(email)) {
      throw new ProductManagementException(
          new ErrorResponse(ErrorEnum.INVALID_EMAIL_EXISTS, false));
    }
  }
}
