
package com.project.product_management.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
    info = @Info(
        title = "Product Management Application",
        version = "1.0.0",
        description = "API services for Product Management",
        contact = @Contact(
            name = "Product Management"
        )
    ),
    servers = {
        @Server(url = "http://localhost:8080/product-management"),
    }
)
@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    final String SECURITY_SCHEME_NAME = "bearerAuth";
    final String SCHEME = "bearer";
    final String BEARER_FORMAT = "JWT";

    return new OpenAPI()
        .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
        .components(
            new Components()
                .addSecuritySchemes(SECURITY_SCHEME_NAME,
                    new SecurityScheme()
                        .name(SECURITY_SCHEME_NAME)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme(SCHEME)
                        .bearerFormat(BEARER_FORMAT)
                )
        );
  }
}