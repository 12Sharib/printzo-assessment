package com.project.product_management.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.product_management.config.ShopifyConfig;
import com.project.product_management.model.entity.Product;
import com.project.product_management.model.repository.ShopifyRepository;
import com.project.product_management.service.constants.ShopifyApiConstants;
import com.project.product_management.service.dto.ProductDto;
import com.project.product_management.service.dto.ProductRequest;
import com.project.product_management.service.interfaces.ShopifyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Service
public class ShopifyServiceImpl implements ShopifyService {

  private final ShopifyConfig shopifyConfig;
  private final RestTemplate restTemplate;
  private final ShopifyRepository productRepository;

  public ShopifyServiceImpl(ShopifyConfig shopifyConfig, ShopifyRepository productRepository) {
    this.shopifyConfig = shopifyConfig;
    this.productRepository = productRepository;
    this.restTemplate = new RestTemplate();
  }

  @Override
  public String getAllProducts() {
    String url = String.format(ShopifyApiConstants.GET_ALL_PRODUCTS, shopifyConfig.getStoreUrl());
    HttpHeaders headers = createHeaders();
    HttpEntity<String> entity = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity,
        String.class);
    return response.getBody();
  }

  @Override
  public String getProductById(Long productId) {
    String url = String.format(ShopifyApiConstants.GET_PUT_DELETE_PRODUCT_BY_ID,
        shopifyConfig.getStoreUrl(), productId);
    HttpHeaders headers = createHeaders();
    HttpEntity<String> entity = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity,
        String.class);
    return response.getBody();
  }

  @Override
  public String addProduct(final ProductDto productDto) {
    final String url = String.format(ShopifyApiConstants.ADD_PRODUCT, shopifyConfig.getStoreUrl());
    ProductRequest productRequest = new ProductRequest(productDto);

    final HttpHeaders headers = createHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<ProductRequest> entity = new HttpEntity<>(productRequest, headers);
    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity,
        String.class);

    String shopifyProductId = extractShopifyProductId(response.getBody());

    Product product = new Product();
    product.setShopifyProductId(shopifyProductId);
    product.setTitle(productDto.getTitle());
    product.setBodyHtml(productDto.getBodyHtml());
    product.setPrice(productDto.getPrice());
    product.setSku(productDto.getSku());
    product.setInventoryQuantity(productDto.getInventoryQuantity());

    productRepository.save(product);

    return response.getBody();
  }

  private String extractShopifyProductId(String body) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode rootNode = objectMapper.readTree(body);

      JsonNode productNode = rootNode.path("product");
      if (productNode.isObject()) {
        return productNode.path("id").asText();
      } else {
        throw new Exception("Product data not found in response");
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public String updateProduct(Long productId, ProductDto productDto) {
    String url = String.format(ShopifyApiConstants.GET_PUT_DELETE_PRODUCT_BY_ID,
        shopifyConfig.getStoreUrl(), productId);
    HttpHeaders headers = createHeaders();
    HttpEntity<ProductDto> entity = new HttpEntity<>(productDto, headers);

    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity,
        String.class);
    return response.getBody();
  }

  @Override
  public String deleteProduct(Long productId) {
    String url = String.format(ShopifyApiConstants.GET_PUT_DELETE_PRODUCT_BY_ID,
        shopifyConfig.getStoreUrl(), productId);
    HttpHeaders headers = createHeaders();
    HttpEntity<String> entity = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, entity,
        String.class);
    return response.getBody();
  }

  private HttpHeaders createHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set(ShopifyApiConstants.SHOPIFY_HEADER, shopifyConfig.getAccessToken());
    headers.setContentType(MediaType.APPLICATION_JSON);
    return headers;
  }
}
