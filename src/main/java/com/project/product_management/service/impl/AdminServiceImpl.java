package com.project.product_management.service.impl;

import com.project.product_management.controller.enums.ErrorEnum;
import com.project.product_management.controller.response.ErrorResponse;
import com.project.product_management.model.entity.Product;
import com.project.product_management.model.repository.ProductRepository;
import com.project.product_management.service.constants.MessageConstants;
import com.project.product_management.service.exceptions.ProductManagementException;
import com.project.product_management.service.interfaces.AdminService;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class AdminServiceImpl implements AdminService {

  private final ProductRepository productRepository;

  public AdminServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public String inactiveProduct(final Integer productId) throws ProductManagementException {
    log.info("Entry inside @class AdminServiceImpl @method inactiveProduct");

    final Optional<Product> optionalProduct = productRepository.findById(productId);

    if (optionalProduct.isPresent()) {
      optionalProduct.get().setIsActive(false);

      productRepository.save(optionalProduct.get());
      return MessageConstants.PRODUCT_STATUS_INACTIVE;
    } else {
      throw new ProductManagementException(new ErrorResponse(ErrorEnum.INVALID_PRODUCT_ID, false));
    }
  }
}
