package com.project.product_management.controller.response;

import com.project.product_management.controller.enums.ErrorEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse extends BaseResponse {

  private String errorCode;


  public ErrorResponse(String message, String errorCode, Boolean success) {
    super(message, success);
    this.errorCode = errorCode;
  }

  public ErrorResponse(ErrorEnum errorEnum, Boolean success) {
    this(errorEnum.getErrorMessage(), errorEnum.getErrorCode(), success);
  }

  public ErrorResponse(String message, boolean success) {
    super(message, success);
  }
}
