# Product Management Application - Docker Setup Guide

## **Prerequisites**

Ensure you have the following installed:
- **Docker** → [Download](https://docs.docker.com/get-docker/)
- **Java 17+** → [Download](https://adoptium.net/)
- **Maven** → [Download](https://maven.apache.org/download.cgi)

---

## **Build and Run the Application**

### **Build the Docker Image**
Navigate to the project root directory and build the Docker image:

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

**URL:** [http://localhost:8080/product-management/swagger-ui.html](http://localhost:8080/product-management/swagger-ui.html)

### **H2 Database Console**
To access the H2 Console inside the container:

**URL:** [http://localhost:8080/product-management/h2-console](http://localhost:8080/product-management/h2-console)

Use the following credentials:
- **JDBC URL:** `jdbc:h2:mem:product_management`
- **Username:** `username`
- **Password:** *(leave empty)*

---

## **API Endpoints** 

### **Admin APIs**
| Method | Endpoint                          | Description                         |
|--------|----------------------------------|-------------------------------------|
| `PUT`  | `/api/admin/inactive/{productId}` | Inactivate a product by ID         |

---

### **Product APIs**
| Method | Endpoint                          | Description                         |
|--------|----------------------------------|-------------------------------------|
| `POST` | `/api/products`                   | Add a new product                  |
| `GET`  | `/api/products`                   | Fetch all products                 |
| `GET`  | `/api/products/{id}`              | Fetch a product by ID              |
| `DELETE` | `/api/products/{id}`           | Remove a product by ID             |
| `PUT`  | `/api/products`                   | Update product details             |
| `PATCH` | `/api/products/quantity`         | Update product quantity            |

---

### **Product Type APIs**
| Method | Endpoint                          | Description                         |
|--------|----------------------------------|-------------------------------------|
| `POST` | `/api/product-types`              | Add a new product type             |
| `GET`  | `/api/product-types`              | Fetch all product types            |
| `PUT`  | `/api/product-types`              | Update a product type              |
| `DELETE` | `/api/product-types/{id}`       | Remove a product type by ID        |

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

