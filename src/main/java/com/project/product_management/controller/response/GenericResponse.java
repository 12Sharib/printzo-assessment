package com.project.product_management.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GenericResponse<T> {

  private boolean success;
  private String message;
  private T data;

  public static <T> GenericResponse<T> error(T data) {
    return GenericResponse.<T>builder().success(false)
        .message("ERROR")
        .data(data)
        .build();
  }

  public static <T> GenericResponse<T> success(T data) {
    return GenericResponse.<T>builder().success(true)
        .message("SUCCESS")
        .data(data)
        .build();
  }
}
