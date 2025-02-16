package com.project.product_management.service.dto;

import com.project.product_management.service.constants.ValidationConstants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductTypeDto {

  @Min(value = 1, message = ValidationConstants.PRODUCT_TYPE_ID_MIN)
  private Integer productTypeId;

  @NotBlank(message = ValidationConstants.PRODUCT_NAME_BLANK)
  @Size(max = 50, message = ValidationConstants.PRODUCT_NAME_MAX_LENGTH)
  @Pattern(regexp = "^[a-zA-Z ]+$", message = ValidationConstants.PRODUCT_NAME_PATTERN)
  private String name;

  @NotBlank(message = ValidationConstants.DESCRIPTION_NAME_BLANK)
  @Size(max = 100, message = ValidationConstants.DESCRIPTION_MAX_LENGTH)
  @Pattern(regexp = "^[a-zA-Z ]+$", message = ValidationConstants.DESCRIPTION_PATTERN)
  private String description;

}
