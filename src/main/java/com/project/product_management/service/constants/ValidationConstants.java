package com.project.product_management.service.constants;

public class ValidationConstants {

  public static final String PRODUCT_NAME_BLANK = "Product name is required. Please provide a valid name.";
  public static final String PRODUCT_NAME_MAX_LENGTH = "Product name cannot exceed 50 characters.";
  public static final String PRICE_NULL = "Price should not be null. Please provide a valid price.";
  public static final String PRICE_MIN = "Price must be greater than or equal to zero";
  public static final String PRODUCT_ID_MIN = "Product id must be greater than or equal to one";
  public static final String PRODUCT_NAME_PATTERN = "Product name must contain only letters.";

  public static final String QUANTITY_NULL = "Quantity should not be null. Please provide a valid quantity.";
  public static final String QUANTITY_MIN = "Quantity must be greater than or equal to one";

  public static final String USERNAME_BLANK = "Username is required. Please provide a valid name.";
  public static final String USERNAME_PATTERN = "Username must contain only letters.";
  public static final String USERNAME_MAX_LENGTH = "Username cannot exceed 50 characters.";
  public static final String PASSWORD_BLANK = "Password cannot be blank";
  public static final String PASSWORD_LENGTH = "Password must be between 8 and 100 characters";
  public static final String PASSWORD_PATTERN = "Password must contain at least one digit, one lowercase, one uppercase, one special character, and no whitespace";

  public static final String EMAIL_BLANK = "Email cannot be blank";
  public static final String EMAIL_INVALID = "Invalid email format";
  public static final String EMAIL_MAX_LENGTH = "Email cannot exceed 100 characters";
  public static final String ROLE_NOT_NULL = "Role cannot be null";

  public static final String DESCRIPTION_NAME_BLANK = "Description is required. Please provide a valid description.";
  public static final String DESCRIPTION_MAX_LENGTH = "Description cannot exceed 50 characters.";
  public static final String DESCRIPTION_PATTERN = "Description must contain only letters.";

  public static final String TYPE_MIN = "Type must be greater than or equal to one";
  public static final String TYPE_NULL = "Type should not be null. Please provide a valid type.";

  public static final String PRODUCT_TYPE_NAME_BLANK = "Product type name is required. Please provide a valid name.";
  public static final String PRODUCT_TYPE_NAME_MAX_LENGTH = "Product type name cannot exceed 50 characters.";
  public static final String PRODUCT_TYPE_ID_MIN = "Product type id must be greater than or equal to one";
  public static final String PRODUCT_TYPE_NAME_PATTERN = "Product name must contain only letters.";


  private ValidationConstants() {
  }
}
