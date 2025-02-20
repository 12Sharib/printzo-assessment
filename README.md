# Product Management Application - Docker Setup Guide

## **Prerequisites**

Ensure you have the following installed:
- **Docker** → [Download](https://docs.docker.com/get-docker/)
- **Java 17+** → [Download](https://adoptium.net/)
- **Maven** → [Download](https://maven.apache.org/download.cgi)
- **Shopify API credentials** → (API Key, Secret, and Store URL)


---

1. Clone the repository:
   ```sh
   git clone <repository_url>
   cd <project_directory>
   ```

2. Configure the `application.properties` or `application.yml` file:
   ```properties
   shopify.api.key=your_api_key
   shopify.api.password=your_api_password
   shopify.store.url=https://yourstore.myshopify.com
   spring.datasource.url=jdbc:h2:mem:product_management
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=username
   spring.datasource.password=
   spring.h2.console.enabled=true
   ```

3. Build and run the application:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

## Using Docker
```bash
mvn clean package
docker build -t product-management .
```

### **Run the Docker Container**
Start the container with the required environment variables and port mappings:

```bash
docker run -p 8080:8080 -e PROFILE=dev,qa,stage or prod product-management
```
---



## **Access the Application**

### **Swagger UI (API Documentation & Testing)**
After running the container, access Swagger UI:

**URL:** [http://localhost:8080/product-management/swagger-ui.html](http://localhost:8080/productDto-management/swagger-ui.html)

### **H2 Database Console**
To access the H2 Console inside the container:

**URL:** [http://localhost:8080/product-management/h2-console](http://localhost:8080/productDto-management/h2-console)

Use the following credentials:
- **JDBC URL:** `jdbc:h2:mem:product_management`
- **Username:** `username`
- **Password:** *(leave empty)*

---

# Shopify Product API Integration

This project integrates Shopify's Product API using Spring Boot to provide CRUD (Create, Read, Update, Delete) operations for managing products. The implementation follows a service-oriented architecture with a dedicated `ShopifyService` interface. Additionally, productDto data is stored both in Shopify and a local H2 database.

## Features Implemented

- **Retrieve All Products**: Fetch all products from Shopify.
- **Retrieve Product by ID**: Get details of a specific productDto using its ID.
- **Add a New Product**: Create a new productDto in Shopify and store it in the local database.
- **Update an Existing Product**: Modify details of an existing productDto in both Shopify and the local database.
- **Delete a Product**: Remove a productDto from Shopify and the local database.
- **Reverse Sync Product Updates**: Sync productDto updates from Shopify to the app database using webhooks (not implemented yet).

## Technologies Used

- Java
- Spring Boot
- Shopify API
- RESTful Web Services
- JSON Processing
- H2 Database (for local storage)

## API Endpoints

| Method | Endpoint               | Description                   |
|--------|------------------------|-------------------------------|
| GET    | `/products`            | Fetch all products           |
| GET    | `/products/{id}`       | Fetch productDto by ID          |
| POST   | `/products`            | Add a new productDto            |
| PUT    | `/products/{id}`       | Update an existing productDto   |
| DELETE | `/products/{id}`       | Delete a productDto by ID       |


### Integrating Shopify Webhooks for Reverse Sync (Not Implemented Yet)

To ensure productDto updates in Shopify are synced to your app database, you need to set up a webhook.

#### Steps to Set Up Webhooks

1. **Deploy Backend Application**
    - Webhooks require a publicly accessible URL. Deploy your backend using a service like AWS, Heroku, or any cloud provider.
    - Ensure HTTPS is enabled.

2. **Register a Webhook in Shopify**
    - Use the Shopify Admin API or Shopify Partner Dashboard to create a webhook.
    - Example request to create a webhook for productDto updates:
      ```sh
      curl -X POST "https://yourstore.myshopify.com/admin/api/2023-01/webhooks.json" \
           -H "Content-Type: application/json" \
           -H "X-Shopify-Access-Token: your_api_token" \
           -d '{
                 "webhook": {
                   "topic": "products/update",
                   "address": "https://your-backend-url.com/webhook/products/update",
                   "format": "json"
                 }
               }'
      ```

3. **Implement Webhook Listener in Backend (Not Implemented Yet)**
    - Create a REST endpoint in Spring Boot to receive webhook payloads:
      ```java
      @RestController
      @RequestMapping("/webhook")
      public class ShopifyWebhookController {
          @PostMapping("/products/update")
          public ResponseEntity<String> handleProductUpdate(@RequestBody String payload) {
              // Process the payload and update the database
              return ResponseEntity.ok("Webhook received");
          }
      }
      ```

4. **Validate Webhook Requests**
    - Verify the webhook request signature using the Shopify API secret to ensure authenticity.

5. **Sync Data to App Database**
    - Parse the webhook payload and update productDto details in your database.

### Testing the API
You can use **Postman** or **cURL** to test the endpoints.

#### Example: Fetch All Products
```sh
curl -X GET "http://localhost:8080/products" -H "Content-Type: application/json"
```

#### Example: Add a Product
```sh
curl -X POST "http://localhost:8080/products" \
     -H "Content-Type: application/json" \
     -d '{"title": "New Product", "price": "10.99"}'
```

---

### **User APIs**
| Method | Endpoint                          | Description                         |
|--------|----------------------------------|-------------------------------------|
| `POST` | `/api/users/register`            | Register a new user                |
| `POST` | `/api/users/login`               | Login a user                       |

---

## **Troubleshooting**

### **Check Logs**
If the application fails to start, check the container logs:

```bash
docker logs <container_id>
```

### **Enter the Running Container**
To debug inside the container:

```bash
docker exec -it <container_id> sh
```

### **Test Connectivity Inside the Container**
To test if the application is responding:

```bash
curl http://localhost:8080/product-management/actuator/health
```

---

## **Stopping and Removing the Container**
To stop and remove the container:

```bash
docker ps
docker stop <container_id>
docker rm <container_id>
```

**Author:** Mohd Sharib Saifi


