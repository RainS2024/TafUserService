package com.Tekarch.UserService.Service;

import com.Tekarch.UserService.DTO.UserDto;
import com.Tekarch.UserService.Service.Interface.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService implements UserInterface {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${db.service.url}")
    private String dataStoreServiceUrl;

    // Register User
    @Override
    public UserDto registerUser(UserDto userDto) {
        try {
            String url = dataStoreServiceUrl + "/users"; // DataStoreService endpoint for creating a user
            HttpEntity<UserDto> request = new HttpEntity<>(userDto);
            ResponseEntity<UserDto> response = restTemplate.exchange(url, HttpMethod.POST, request, UserDto.class);
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Failed to register user: " + e.getMessage(), e);
        }
    }

    // Get User by ID
    @Override
    public UserDto getUserById(Long userId) {
        try {
            String url = dataStoreServiceUrl + "/users/" + userId;
            ResponseEntity<UserDto> response = restTemplate.exchange(url, HttpMethod.GET, null, UserDto.class);
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch user with ID " + userId + ": " + e.getMessage(), e);
        }
    }

    // Update User by ID
    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        try {
            String url = dataStoreServiceUrl + "/users/" + userId;
            HttpEntity<UserDto> request = new HttpEntity<>(userDto);
            ResponseEntity<UserDto> response = restTemplate.exchange(url, HttpMethod.PUT, request, UserDto.class);
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Failed to update user with ID " + userId + ": " + e.getMessage(), e);
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        try {
            String url = dataStoreServiceUrl + "/users";
            ResponseEntity<List<UserDto>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<UserDto>>() {}
            );
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch users: " + e.getMessage(), e);
        }
    }
}
