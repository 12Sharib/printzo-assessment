package com.project.product_management.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {

  private String message;
  private Boolean success;

  BaseResponse(final String message, final Boolean success) {
    this.message = message;
    this.success = success;
  }

}
