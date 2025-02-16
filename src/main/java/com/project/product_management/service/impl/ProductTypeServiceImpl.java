package com.project.product_management.service.impl;

import com.project.product_management.controller.enums.ErrorEnum;
import com.project.product_management.controller.response.ErrorResponse;
import com.project.product_management.model.entity.ProductType;
import com.project.product_management.model.repository.ProductTypeRepository;
import com.project.product_management.service.constants.MessageConstants;
import com.project.product_management.service.dto.ProductTypeDto;
import com.project.product_management.service.dto.UpdateProductTypeDto;
import com.project.product_management.service.exceptions.ProductManagementException;
import com.project.product_management.service.interfaces.ProductTypeService;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ProductTypeServiceImpl implements ProductTypeService {

  private final ProductTypeRepository productTypeRepository;

  public ProductTypeServiceImpl(ProductTypeRepository productTypeRepository) {
    this.productTypeRepository = productTypeRepository;
  }

  @Override
  public String addType(final ProductTypeDto typeDto) throws ProductManagementException {
    log.info("Entry inside @class ProductTypeServiceImpl @method addType");

    validateTypeNameAlreadyExists(typeDto.getName());

    final ProductType productType = new ProductType();
    productType.setName(typeDto.getName());
    productType.setDescription(typeDto.getDescription());

    productTypeRepository.save(productType);
    return MessageConstants.PRODUCT_TYPE_ADDED_SUCCESS;
  }

  @Override
  public String updateType(final UpdateProductTypeDto productDto)
      throws ProductManagementException {
    validateProductTypeId(productDto.getProductTypeId());

    validateTypeNameAlreadyExists(productDto.getName());

    final ProductType productType = new ProductType();
    productType.setName(productDto.getName());
    productType.setDescription(productDto.getDescription());

    productTypeRepository.save(productType);
    return MessageConstants.PRODUCT_TYPE_UPDATE_SUCCESS;
  }

  @Override
  public List<ProductType> getAllProductTypes() {
    log.info("Entry inside @class ProductTypeServiceImpl @method getAllProductTypes");
    return productTypeRepository.findAll();
  }

  @Override
  public String removeProduct(final Integer productTypeId) throws ProductManagementException {
    log.info("Entry inside @class ProductTypeServiceImpl @method removeProduct");
    validateProductTypeId(productTypeId);

    productTypeRepository.deleteById(productTypeId);

    return MessageConstants.PRODUCT_TYPE_DELETE_SUCCESS;
  }

  private void validateProductTypeId(final Integer productTypeId)
      throws ProductManagementException {
    if (!productTypeRepository.existsById(productTypeId)) {
      throw new ProductManagementException(
          new ErrorResponse(ErrorEnum.INVALID_PRODUCT_TYPE_ID, false));
    }
  }

  private void validateTypeNameAlreadyExists(final String name) throws ProductManagementException {
    if (productTypeRepository.existsByName(name)) {
      throw new ProductManagementException(
          new ErrorResponse(ErrorEnum.INVALID_PRODUCT_TYPE, false));
    }
  }
}
