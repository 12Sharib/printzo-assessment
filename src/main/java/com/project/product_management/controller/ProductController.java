package com.project.product_management.controller;

import com.project.product_management.controller.response.GenericResponse;
import com.project.product_management.service.constants.ValidationConstants;
import com.project.product_management.service.dto.ProductDto;
import com.project.product_management.service.dto.UpdateProductDto;
import com.project.product_management.service.dto.UpdateQuantityDto;
import com.project.product_management.service.exceptions.ProductManagementException;
import com.project.product_management.service.interfaces.ProductService;
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
@RequestMapping("/product")
@Tag(name = "Product Controller")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }


  @Operation(
      summary = "Add product",
      description = "API to add a Product.",
      responses = {
          @ApiResponse(responseCode = "200", description = "Product Added successfully"),
          @ApiResponse(responseCode = "500", description = "Internal server error while processing the request.")
      }
  )
  @PostMapping("/add")
  public ResponseEntity<GenericResponse<String>> addProduct(
      @Valid @RequestBody ProductDto productDto) throws ProductManagementException {
    log.info("Entry inside @class ProductController @method addProduct");

    return ResponseEntity.ok(GenericResponse.success(productService.addProduct(productDto)));
  }

  @Operation(
      summary = "Update product",
      description = "API to update a product item.",
      responses = {
          @ApiResponse(responseCode = "200", description = "Product updated successfully"),
          @ApiResponse(responseCode = "500", description = "Internal server error while processing the request.")
      }
  )
  @PutMapping("/update")
  public ResponseEntity<GenericResponse<String>> updateProduct(
      @Valid @RequestBody UpdateProductDto productDto) throws ProductManagementException {
    log.info("Entry inside @class productController @method updateProduct");

    return ResponseEntity.ok(GenericResponse.success(productService.updateProduct(productDto)));
  }

  @Operation(
      summary = "Fetch all products",
      description = "API to fetch a list of all products.",
      responses = {
          @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of products."),
          @ApiResponse(responseCode = "500", description = "Internal server error while processing the request.")
      }
  )
  @GetMapping("/products")
  public ResponseEntity<GenericResponse> getAllProducts() {
    log.info("Entry inside @class ProductController @method getAllProducts");

    return ResponseEntity.ok(GenericResponse.success(productService.getAllProducts()));
  }

  @Operation(
      summary = "Get product",
      description = "API to get a product.",
      responses = {
          @ApiResponse(responseCode = "200", description = "Get product by id"),
          @ApiResponse(responseCode = "500", description = "Internal server error while processing the request.")
      }
  )
  @GetMapping()
  public ResponseEntity<GenericResponse> getProductById(
      @Min(value = 1, message = ValidationConstants.PRODUCT_ID_MIN)
      @RequestParam("id") Integer productId) throws ProductManagementException {
    log.info("Entry inside @class productController @method getProductById");

    return ResponseEntity.ok(GenericResponse.success(productService.getProductById(productId)));
  }


  @Operation(
      summary = "Remove product",
      description = "API to remove a product item permanently.",
      responses = {
          @ApiResponse(responseCode = "200", description = "Product removed successfully"),
          @ApiResponse(responseCode = "500", description = "Internal server error while processing the request.")
      }
  )
  @DeleteMapping("/remove")
  public ResponseEntity<GenericResponse<String>> removeProduct(
      @Min(value = 1, message = ValidationConstants.PRODUCT_ID_MIN)
      @RequestParam("id") Integer productId) throws ProductManagementException {
    log.info("Entry inside @class productController @method removeProduct");

    return ResponseEntity.ok(GenericResponse.success(productService.removeProduct(productId)));
  }

  @Operation(
      summary = "Update product quantity",
      description = "API to update the quantity of a product item.",
      responses = {
          @ApiResponse(responseCode = "200", description = "Product quantity updated successfully"),
          @ApiResponse(responseCode = "500", description = "Internal server error while processing the request.")
      }
  )
  @PutMapping("/quantity/update")
  public ResponseEntity<GenericResponse<String>> updateQuantity(
      @Valid @RequestBody UpdateQuantityDto updateQuantityDto)
      throws ProductManagementException {
    log.info("Entry inside @class productController @method updateQuantity");

    return ResponseEntity.ok(
        GenericResponse.success(productService.updateQuantity(updateQuantityDto)));
  }
}
