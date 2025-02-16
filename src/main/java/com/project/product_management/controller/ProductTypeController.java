package com.project.product_management.controller;

import com.project.product_management.controller.response.GenericResponse;
import com.project.product_management.service.constants.ValidationConstants;
import com.project.product_management.service.dto.ProductTypeDto;
import com.project.product_management.service.dto.UpdateProductTypeDto;
import com.project.product_management.service.exceptions.ProductManagementException;
import com.project.product_management.service.interfaces.ProductTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/type")
@Tag(name = "Product Type Controller")
public class ProductTypeController {

  private final ProductTypeService productTypeService;

  public ProductTypeController(ProductTypeService productTypeService) {
    this.productTypeService = productTypeService;
  }

  @Operation(
      summary = "Add type",
      description = "API to add a type.",
      responses = {
          @ApiResponse(responseCode = "200", description = "Type Added successfully"),
          @ApiResponse(responseCode = "500", description = "Internal server error while processing the request.")
      }
  )
  @PostMapping("/add")
  public ResponseEntity<GenericResponse<String>> addType(
      @Valid @RequestBody ProductTypeDto typeDto) throws ProductManagementException {
    log.info("Entry inside @class typeController @method addType");

    return ResponseEntity.ok(GenericResponse.success(productTypeService.addType(typeDto)));
  }

  @Operation(
      summary = "Update type",
      description = "API to update a type.",
      responses = {
          @ApiResponse(responseCode = "200", description = "Type updated successfully"),
          @ApiResponse(responseCode = "500", description = "Internal server error while processing the request.")
      }
  )
  @PutMapping("/update")
  public ResponseEntity<GenericResponse<String>> updateType(
      @Valid @RequestBody UpdateProductTypeDto productDto) throws ProductManagementException {
    log.info("Entry inside @class productController @method updateType");

    return ResponseEntity.ok(GenericResponse.success(productTypeService.updateType(productDto)));
  }

  @Operation(
      summary = "Fetch all types",
      description = "API to fetch a list of all types.",
      responses = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of types."),
          @ApiResponse(responseCode = "500", description = "Internal server error while processing the request.")
      }
  )
  @GetMapping("/types")
  public ResponseEntity<GenericResponse> getAllTypes() {
    log.info("Entry inside @class ProductController @method getAllProducts");

    return ResponseEntity.ok(GenericResponse.success(productTypeService.getAllProductTypes()));
  }

  @Operation(
      summary = "Remove type",
      description = "API to remove a type permanently.",
      responses = {
          @ApiResponse(responseCode = "200", description = "Product type removed successfully"),
          @ApiResponse(responseCode = "500", description = "Internal server error while processing the request.")
      }
  )
  @DeleteMapping("/remove")
  public ResponseEntity<GenericResponse<String>> removeProduct(
      @Min(value = 1, message = ValidationConstants.PRODUCT_TYPE_ID_MIN)
      @RequestParam("id") Integer productId) throws ProductManagementException {
    log.info("Entry inside @class productController @method removeProduct");

    return ResponseEntity.ok(GenericResponse.success(productTypeService.removeProduct(productId)));
  }

}
