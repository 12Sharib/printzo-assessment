package com.project.product_management.service.exceptions;

import com.project.product_management.controller.response.ErrorResponse;
import com.project.product_management.controller.response.GenericResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

  @ExceptionHandler(ProductManagementException.class)
  public ResponseEntity<ErrorResponse> handleProductManagementException(
      final ProductManagementException exception) {
    return ResponseEntity.badRequest().body(
        new ErrorResponse(exception.getErrorResponse().getMessage(),
            exception.getErrorResponse().getErrorCode(), false));
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> handleRuntimeException(
      final Exception exception) {
    return ResponseEntity.internalServerError().body(
        new ErrorResponse(exception.getMessage(), false));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<GenericResponse<Map<String, String>>> methodArgumentNotValidException(
      final MethodArgumentNotValidException methodArgumentNotValidException) {
    log.error("MethodArgumentNotValidException: ", methodArgumentNotValidException);

    final List<FieldError> fieldErrors = methodArgumentNotValidException.getFieldErrors();
    final Map<String, String> errors = new HashMap<>();

    fieldErrors.forEach(
        fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GenericResponse.error(errors));
  }

}
