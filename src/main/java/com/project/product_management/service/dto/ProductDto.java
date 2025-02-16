package com.project.product_management.service.dto;

import com.project.product_management.service.constants.ValidationConstants;
import jakarta.validation.constraints.Min;
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
public class ProductDto {

  @NotBlank(message = ValidationConstants.PRODUCT_NAME_BLANK)
  @Size(max = 50, message = ValidationConstants.PRODUCT_NAME_MAX_LENGTH)
  @Pattern(regexp = "^[a-zA-Z ]+$", message = ValidationConstants.PRODUCT_NAME_PATTERN)
  private String name;

  @NotNull(message = ValidationConstants.PRICE_NULL)
  @Min(value = 0, message = ValidationConstants.PRICE_MIN)
  private Integer price;

  @NotNull(message = ValidationConstants.QUANTITY_NULL)
  @Min(value = 1, message = ValidationConstants.QUANTITY_MIN)
  private Integer quantity;

  @NotBlank(message = ValidationConstants.DESCRIPTION_NAME_BLANK)
  @Size(max = 100, message = ValidationConstants.DESCRIPTION_MAX_LENGTH)
  @Pattern(regexp = "^[a-zA-Z ]+$", message = ValidationConstants.DESCRIPTION_PATTERN)
  private String description;

  @NotNull(message = ValidationConstants.TYPE_NULL)
  @Min(value = 1, message = ValidationConstants.TYPE_MIN)
  private Integer type;
}
