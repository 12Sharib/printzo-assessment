package com.project.product_management.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductRequest {
  @JsonProperty("product")
  private ProductDto productDto;

  public ProductRequest(ProductDto productDto) {
    this.productDto = productDto;
  }
}
