package com.project.product_management.service.dto;

import com.project.product_management.service.constants.ValidationConstants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateQuantityDto {

  @Min(value = 1, message = ValidationConstants.PRODUCT_ID_MIN)
  private Integer productId;

  @Min(value = 1, message = "Stock quantity must be at least 1")
  private Integer quantity;

  @NotNull(message = "Action type cannot be null (use 'ADD' or 'REMOVE')")
  private String action;
}
