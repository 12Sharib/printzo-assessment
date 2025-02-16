package com.project.product_management.service.interfaces;

import com.project.product_management.service.dto.LoginRequestDto;
import com.project.product_management.service.dto.RegisterUserDto;
import com.project.product_management.service.exceptions.ProductManagementException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

  String registerUser(RegisterUserDto registerUserDto) throws ProductManagementException;

  String loginUser(LoginRequestDto loginRequestDto) throws ProductManagementException;
}
