package com.project.product_management.service.interfaces;

import com.project.product_management.service.exceptions.ProductManagementException;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

  String inactiveProduct(final Integer productId) throws ProductManagementException;
}
