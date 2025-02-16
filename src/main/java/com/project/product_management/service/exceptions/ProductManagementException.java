package com.project.product_management.service.exceptions;

import com.project.product_management.controller.response.ErrorResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductManagementException extends Exception {

  private final ErrorResponse errorResponse;

  public ProductManagementException(final ErrorResponse errorResponse) {
    this.errorResponse = errorResponse;
  }

}
