package com.project.product_management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.project.product_management.controller.enums.ErrorEnum;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

class ProductServiceImplTest extends TestBase {

  @MockBean
  private ProductRepository productRepository;

  @MockBean
  private ProductTypeRepository productTypeRepository;

  @Autowired
  private ProductService productService;

  private ProductDto productDto;
  private Product product;

  @BeforeEach
  void setUp() {
    productDto = new ProductDto("Test Product", 100, 10, "Description", 1);
    product = new Product(1, "Test Product", "Sample Description", 10, 100, 1, true);
  }

  @Test
  void testAddProductSuccess() throws ProductManagementException {
    when(productTypeRepository.existsById(1)).thenReturn(true);
    when(productRepository.existsByName("Test Product")).thenReturn(false);
    when(productRepository.save(any(Product.class))).thenReturn(product);

    String response = productService.addProduct(productDto);
    assertEquals(MessageConstants.PRODUCT_ADDED_SUCCESS, response);
  }

  @Test
  void testAddProductFailDuplicateName() {
    when(productTypeRepository.existsById(1)).thenReturn(true);
    when(productRepository.existsByName("Test Product")).thenReturn(true);

    ProductManagementException exception = assertThrows(ProductManagementException.class, () -> {
      productService.addProduct(productDto);
    });
    assertEquals(ErrorEnum.INVALID_PRODUCT.getErrorMessage(),
        exception.getErrorResponse().getMessage());
  }

  @Test
  void testGetAllProductsSuccess() {
    when(productRepository.findAll()).thenReturn(List.of(product));

    List<Product> products = productService.getAllProducts();
    assertEquals(1, products.size());
  }

  @Test
  void testGetProductByIdSuccess() throws ProductManagementException {
    when(productRepository.existsById(1)).thenReturn(true);
    when(productRepository.findById(1)).thenReturn(Optional.of(product));

    Product retrievedProduct = productService.getProductById(1);
    assertNotNull(retrievedProduct);
    assertEquals(1, retrievedProduct.getId());
  }

  @Test
  void testGetProductByIdFailNotFound() {
    when(productRepository.findById(1)).thenReturn(Optional.empty());

    ProductManagementException exception = assertThrows(ProductManagementException.class, () -> {
      productService.getProductById(1);
    });
    assertEquals(ErrorEnum.INVALID_PRODUCT_ID.getErrorMessage(),
        exception.getErrorResponse().getMessage());
  }

  @Test
  void testRemoveProductSuccess() throws ProductManagementException {
    when(productRepository.existsById(1)).thenReturn(true);
    doNothing().when(productRepository).deleteById(1);

    String response = productService.removeProduct(1);
    assertEquals(MessageConstants.PRODUCT_DELETE_SUCCESS, response);
  }

  @Test
  void testRemoveProductFailInvalidProductId() {
    when(productRepository.existsById(1)).thenReturn(false);

    ProductManagementException exception = assertThrows(ProductManagementException.class, () -> {
      productService.removeProduct(1);
    });
    assertEquals(ErrorEnum.INVALID_PRODUCT_ID.getErrorMessage(),
        exception.getErrorResponse().getMessage());
  }

  @Test
  void testUpdateQuantitySuccessAdd() throws ProductManagementException {
    UpdateQuantityDto updateDto = new UpdateQuantityDto(1, 5, "ADD");
    when(productRepository.findById(1)).thenReturn(Optional.of(product));

    String response = productService.updateQuantity(updateDto);
    assertEquals(MessageConstants.QUANTITY_SUCCESS, response);
  }


  @Test
  void testUpdateQuantityFailInvalidAction() {
    UpdateQuantityDto updateDto = new UpdateQuantityDto(1, 5, "INVALID");
    when(productRepository.findById(1)).thenReturn(Optional.of(product));

    ProductManagementException exception = assertThrows(ProductManagementException.class, () -> {
      productService.updateQuantity(updateDto);
    });
    assertEquals(ErrorEnum.INVALID_ACTION_FOR_QUANTITY.getErrorMessage(),
        exception.getErrorResponse().getMessage());
  }


  @Test
  void testUpdateProductSuccess() throws ProductManagementException {
    UpdateProductDto updateDto = new UpdateProductDto(1, "Updated Product", 100, 5, "New Desc");
    when(productRepository.findById(1)).thenReturn(Optional.of(product));

    String response = productService.updateProduct(updateDto);
    assertEquals(MessageConstants.PRODUCT_DETAILS_UPDATE, response);
  }

  @Test
  void testUpdateProductFailInvalidProductId() {
    UpdateProductDto updateDto = new UpdateProductDto(1, "Updated Product", 150, 5, "New Desc");
    when(productRepository.findById(1)).thenReturn(Optional.empty());

    ProductManagementException exception = assertThrows(ProductManagementException.class, () -> {
      productService.updateProduct(updateDto);
    });
    assertEquals(ErrorEnum.INVALID_PRODUCT_ID.getErrorMessage(),
        exception.getErrorResponse().getMessage());
  }
}
