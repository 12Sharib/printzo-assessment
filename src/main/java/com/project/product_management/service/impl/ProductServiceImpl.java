package com.project.product_management.service.impl;

import com.project.product_management.controller.enums.ErrorEnum;
import com.project.product_management.controller.response.ErrorResponse;
import com.project.product_management.model.entity.Product;
import com.project.product_management.model.repository.ProductRepository;
import com.project.product_management.model.repository.ProductTypeRepository;
import com.project.product_management.service.constants.MessageConstants;
import com.project.product_management.service.dto.ProductDto;
import com.project.product_management.service.dto.UpdateProductDto;
import com.project.product_management.service.dto.UpdateQuantityDto;
import com.project.product_management.service.exceptions.ProductManagementException;
import com.project.product_management.service.interfaces.ProductService;
import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ProductServiceImpl implements ProductService {

  private final ProductTypeRepository productTypeRepository;
  private final ProductRepository productRepository;

  public ProductServiceImpl(ProductTypeRepository productTypeRepository,
      ProductRepository productRepository) {
    this.productTypeRepository = productTypeRepository;
    this.productRepository = productRepository;
  }

  @Override
  public String addProduct(final ProductDto productDto) throws ProductManagementException {
    log.info("Entry inside @class ProductServiceImpl @method addProduct");

    // Validate product type
    validateProductType(productDto.getType());

    validateProductNameIsExists(productDto.getName());

    final Product product = new Product();
    product.setQuantity(productDto.getQuantity());
    product.setProductTypeId(productDto.getType());
    product.setName(productDto.getName());
    product.setDescription(productDto.getDescription());
    product.setPrice(productDto.getPrice());
    product.setIsActive(true);

    productRepository.save(product);

    return MessageConstants.PRODUCT_ADDED_SUCCESS;
  }

  private void validateProductNameIsExists(
      final String name) throws ProductManagementException {

    if (productRepository.existsByName(name)) {
      throw new ProductManagementException(new ErrorResponse(ErrorEnum.INVALID_PRODUCT, false));
    }
  }

  @Override
  public List<Product> getAllProducts() {
    log.info("Entry inside @class ProductServiceImpl @method getAllProducts");

    return productRepository.findAll();
  }

  @Override
  public String removeProduct(final Integer productId) throws ProductManagementException {
    log.info("Entry inside @class ProductServiceImpl @method removeProduct");

    validateProductId(productId);

    productRepository.deleteById(productId);
    return MessageConstants.PRODUCT_DELETE_SUCCESS;
  }

  @Override
  public String updateQuantity(final UpdateQuantityDto updateQuantityDto)
      throws ProductManagementException {
    log.info("Entry inside @class ProductServiceImpl @method updateQuantity");

    final Optional<Product> optionalProduct = productRepository.findById(
        updateQuantityDto.getProductId());

    if (optionalProduct.isEmpty()) {
      throw new ProductManagementException(new ErrorResponse(ErrorEnum.INVALID_PRODUCT_ID, false));
    }

    final Product product = optionalProduct.get();

    if ("ADD".equalsIgnoreCase(updateQuantityDto.getAction())) {
      product.setQuantity(product.getQuantity() + updateQuantityDto.getQuantity());
    } else if ("REMOVE".equalsIgnoreCase(updateQuantityDto.getAction())) {
      if (product.getQuantity() < updateQuantityDto.getQuantity()) {
        throw new ProductManagementException(new ErrorResponse(ErrorEnum.INVALID_QUANTITY, false));
      }
      product.setQuantity(product.getQuantity() - updateQuantityDto.getQuantity());
    } else {
      throw new ProductManagementException(
          new ErrorResponse(ErrorEnum.INVALID_ACTION_FOR_QUANTITY, false));
    }

    productRepository.save(product);

    return MessageConstants.QUANTITY_SUCCESS;
  }

  @Override
  public String updateProduct(final UpdateProductDto productDto) throws ProductManagementException {
    log.info("Entry inside @class ProductServiceImpl @method updateProduct");

    validateProductNameIsExists(productDto.getName());

    final Optional<Product> product = productRepository.findById(productDto.getProductId());

    if (product.isPresent()) {
      product.get().setName(productDto.getName());
      product.get().setPrice(productDto.getPrice());
      product.get().setQuantity(productDto.getQuantity());
      product.get().setDescription(productDto.getDescription());

      productRepository.save(product.get());

      return MessageConstants.PRODUCT_DETAILS_UPDATE;
    } else {
      throw new ProductManagementException(new ErrorResponse(ErrorEnum.INVALID_PRODUCT_ID, false));
    }
  }

  @Override
  public Product getProductById(final Integer productId) throws ProductManagementException {
    log.info("Entry inside @class ProductServiceImpl @method getProductById");

    validateProductId(productId);
    return productRepository.findById(productId).get();
  }

  private void validateProductId(final Integer productId) throws ProductManagementException {
    if (!productRepository.existsById(productId)) {
      throw new ProductManagementException(new ErrorResponse(ErrorEnum.INVALID_PRODUCT_ID, false));
    }
  }

  private void validateProductType(final Integer typeId) throws ProductManagementException {
    if (!productTypeRepository.existsById(typeId)) {
      throw new ProductManagementException(
          new ErrorResponse(ErrorEnum.INVALID_PRODUCT_TYPE_ID, false));
    }
  }
}
