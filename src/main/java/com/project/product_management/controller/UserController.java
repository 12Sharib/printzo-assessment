package com.project.product_management.controller;


import com.project.product_management.controller.response.GenericResponse;
import com.project.product_management.service.dto.LoginRequestDto;
import com.project.product_management.service.dto.RegisterUserDto;
import com.project.product_management.service.exceptions.ProductManagementException;
import com.project.product_management.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/user")
@Tag(name = "User Controller")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Operation(
      summary = "Register User",
      description = "API to register a new user.",
      responses = {
          @ApiResponse(responseCode = "200", description = "User registered successfully"),
          @ApiResponse(responseCode = "400", description = "Invalid request data"),
          @ApiResponse(responseCode = "500", description = "Internal server error")
      }
  )
  @PostMapping("/register")
  public ResponseEntity<GenericResponse<String>> registerUser(
      @Valid @RequestBody RegisterUserDto registerUserDto)
      throws ProductManagementException {
    log.info("Entry inside @class UserController @method registerUser");

    return ResponseEntity.ok()
        .body(GenericResponse.success(userService.registerUser(registerUserDto)));
  }

  @Operation(
      summary = "User Login",
      description = "API to authenticate a user.",
      responses = {
          @ApiResponse(responseCode = "200", description = "User logged in successfully"),
          @ApiResponse(responseCode = "401", description = "Invalid credentials"),
          @ApiResponse(responseCode = "500", description = "Internal server error")
      }
  )
  @PostMapping("/login")
  public ResponseEntity<GenericResponse<String>> loginUser(
      @Valid @RequestBody LoginRequestDto loginRequestDto)
      throws ProductManagementException {
    log.info("Entry inside @class UserController @method loginUser");

    return ResponseEntity.ok()
        .body(GenericResponse.success(userService.loginUser(loginRequestDto)));
  }
}
