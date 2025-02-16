package com.project.product_management.controller;

import com.project.product_management.controller.response.GenericResponse;
import com.project.product_management.service.constants.ValidationConstants;
import com.project.product_management.service.exceptions.ProductManagementException;
import com.project.product_management.service.interfaces.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/admin")
@Tag(name = "Admin Controller")
public class AdminController {

  private final AdminService adminService;

  public AdminController(AdminService adminService) {
    this.adminService = adminService;
  }


  @Operation(
      summary = "Mark product as inactive",
      description = "API to mark a product as inactive.",
      responses = {
          @ApiResponse(responseCode = "200", description = "Product marked as inactive successfully"),
          @ApiResponse(responseCode = "500", description = "Internal server error while processing the request.")
      }
  )
  @DeleteMapping("/inactive/product")
  public ResponseEntity<GenericResponse<String>> inactiveProduct(
      @Min(value = 1, message = ValidationConstants.PRODUCT_ID_MIN)
      @RequestParam("id") Integer productId) throws ProductManagementException {
    log.info("Entry inside @class AdminController @method inactiveProduct");

    return ResponseEntity.ok(GenericResponse.success(adminService.inactiveProduct(productId)));
  }
}
