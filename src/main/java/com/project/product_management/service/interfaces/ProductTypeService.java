package com.project.product_management.service.interfaces;

import com.project.product_management.model.entity.ProductType;
import com.project.product_management.service.dto.ProductTypeDto;
import com.project.product_management.service.dto.UpdateProductTypeDto;
import com.project.product_management.service.exceptions.ProductManagementException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface ProductTypeService {

  String addType(ProductTypeDto typeDto) throws ProductManagementException;

  String updateType(UpdateProductTypeDto productDto) throws ProductManagementException;

  List<ProductType> getAllProductTypes();

  String removeProduct(Integer productId) throws ProductManagementException;
}
