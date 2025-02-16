package com.project.product_management.service.interfaces;

import com.project.product_management.model.entity.Product;
import com.project.product_management.service.dto.ProductDto;
import com.project.product_management.service.dto.UpdateProductDto;
import com.project.product_management.service.dto.UpdateQuantityDto;
import com.project.product_management.service.exceptions.ProductManagementException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

  String addProduct(ProductDto productDto) throws ProductManagementException;

  List<Product> getAllProducts();

  String removeProduct(Integer productId) throws ProductManagementException;

  String updateQuantity(UpdateQuantityDto updateQuantityDto) throws ProductManagementException;

  String updateProduct(UpdateProductDto productDto) throws ProductManagementException;

  Product getProductById(Integer productId) throws ProductManagementException;
}
