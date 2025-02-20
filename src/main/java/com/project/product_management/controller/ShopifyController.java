package com.project.product_management.controller;

import com.project.product_management.service.dto.ProductDto;
import com.project.product_management.service.interfaces.ShopifyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopify")
public class ShopifyController {

    private final ShopifyService shopifyService;

    public ShopifyController(ShopifyService shopifyService) {
        this.shopifyService = shopifyService;
    }

    /**
     * Fetch all products from Shopify store.
     */
    @GetMapping("/products")
    public ResponseEntity<String> getAllProducts() {
        String response = shopifyService.getAllProducts();
        return ResponseEntity.ok(response);
    }

    /**
     * Fetch a specific product by ID from Shopify.
     */
    @GetMapping("/products/{productId}")
    public ResponseEntity<String> getProductById(@PathVariable Long productId) {
        String response = shopifyService.getProductById(productId);
        return ResponseEntity.ok(response);
    }

    /**
     * Add a new product to Shopify.
     */
    @PostMapping("/products")
    public ResponseEntity<String> addProduct(@RequestBody ProductDto productDto) {
        String response = shopifyService.addProduct(productDto);
        return ResponseEntity.ok(response);
    }

    /**
     * Update an existing product in Shopify.
     */
    @PutMapping("/products/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Long productId,
        @RequestBody ProductDto productDto) {
        String response = shopifyService.updateProduct(productId, productDto);
        return ResponseEntity.ok(response);
    }

    /**
     * Delete a product from Shopify.
     */
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        String response = shopifyService.deleteProduct(productId);
        return ResponseEntity.ok(response);
    }
}
