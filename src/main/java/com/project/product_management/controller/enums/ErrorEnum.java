package com.project.product_management.controller.enums;

import lombok.Getter;

@Getter
public enum ErrorEnum {
  // PRODUCT EXCEPTIONS CODE STARTS 100-00
  INVALID_PRODUCT_ID("100-01", "Invalid product id, please provide valid id"),
  INVALID_QUANTITY("100-02", "Invalid quantity, provided quantity is not exist in stock."),
  INVALID_ACTION_FOR_QUANTITY("100-03", "Invalid action. Use 'ADD' or 'REMOVE'."),
  INVALID_PRODUCT("100-04", "Invalid product, provided already exists."),

  //  TYPE EXCEPTIONS CODE STARTS 200-00
  INVALID_PRODUCT_TYPE("200-01", "Invalid product type, provided already exists."),
  INVALID_PRODUCT_TYPE_ID("200-02", "Invalid product type id, please provide valid id"),


  //  USER EXCEPTIONS CODE STARTS 500-00
  INVALID_EMAIL_EXISTS("500-01", "Invalid email, provided email already exists."),
  USER_NOT_FOUND("500-02", "Invalid email, provide valid email."),
  INVALID_CREDENTIALS("500-03",
      "Invalid password, please provide valid password.");
  private final String errorMessage;
  private final String errorCode;

  ErrorEnum(final String errorCode, final String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }
}
