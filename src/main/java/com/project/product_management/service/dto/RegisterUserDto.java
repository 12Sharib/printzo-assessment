package com.project.product_management.service.dto;


import com.project.product_management.controller.enums.Role;
import com.project.product_management.service.constants.ValidationConstants;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {

  @NotBlank(message = ValidationConstants.USERNAME_BLANK)
  @Size(max = 50, message = ValidationConstants.USERNAME_MAX_LENGTH)
  @Pattern(regexp = "^[a-zA-Z ]+$", message = ValidationConstants.USERNAME_PATTERN)
  private String username;

  @NotBlank(message = ValidationConstants.EMAIL_BLANK)
  @Email(message = ValidationConstants.EMAIL_INVALID)
  @Size(max = 100, message = ValidationConstants.EMAIL_MAX_LENGTH)
  private String email;

  @NotBlank(message = ValidationConstants.PASSWORD_BLANK)
  @Size(min = 8, max = 100, message = ValidationConstants.PASSWORD_LENGTH)
  @Pattern(
      regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
      message = ValidationConstants.PASSWORD_PATTERN
  )
  private String password;

  @NotNull(message = ValidationConstants.ROLE_NOT_NULL)
  @Enumerated(EnumType.STRING)
  private Role role;
}
