package com.project.product_management.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductDto {

  private String title;
  @JsonProperty("body_html")
  private String bodyHtml;
  private Double price;
  private String sku;
  private String inventoryQuantity;
}

