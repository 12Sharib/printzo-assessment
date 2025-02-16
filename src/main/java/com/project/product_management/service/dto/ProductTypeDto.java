package com.project.product_management.service.dto;

import com.project.product_management.service.constants.ValidationConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductTypeDto {

  @NotBlank(message = ValidationConstants.PRODUCT_TYPE_NAME_BLANK)
  @Size(max = 50, message = ValidationConstants.PRODUCT_TYPE_NAME_MAX_LENGTH)
  @Pattern(regexp = "^[a-zA-Z ]+$", message = ValidationConstants.PRODUCT_TYPE_NAME_PATTERN)
  private String name;

  @NotBlank(message = ValidationConstants.DESCRIPTION_NAME_BLANK)
  @Size(max = 100, message = ValidationConstants.DESCRIPTION_MAX_LENGTH)
  @Pattern(regexp = "^[a-zA-Z ]+$", message = ValidationConstants.DESCRIPTION_PATTERN)
  private String description;

}
