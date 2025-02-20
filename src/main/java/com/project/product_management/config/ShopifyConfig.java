package com.project.product_management.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShopifyConfig {

    @Value("${shopify.store-url}")
    private String storeUrl;

    @Value("${shopify.access-token}")
    private String accessToken;

    public String getStoreUrl() {
        return storeUrl;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
