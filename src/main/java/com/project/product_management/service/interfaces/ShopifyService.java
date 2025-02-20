package com.project.product_management.service.interfaces;

import com.project.product_management.service.dto.ProductDto;
import org.springframework.stereotype.Service;

@Service
public interface ShopifyService {

  String getAllProducts();

  String getProductById(Long productId);

  String addProduct(ProductDto productDto);

  String updateProduct(Long productId, ProductDto productDto);

  String deleteProduct(Long productId);
}
