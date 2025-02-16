package com.project.product_management;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@Log4j2
public class ProductManagementApplication {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(ProductManagementApplication.class);

    ConfigurableEnvironment environment = app.run(args).getEnvironment();
    String activeProfile = environment.getActiveProfiles().length > 0 ? environment.getActiveProfiles()[0] : "default";

    log.info("Application started successfully in {} environment", activeProfile);
  }

}
