package com.medlink.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medlink.models.ErrorResponse;
import com.medlink.models.LoginRequest;
import com.medlink.models.LoginResponse;
import com.medlink.services.UserService;
@RestController
public class Login {
    @Autowired
    private UserService uService;
    private LoginResponse responseObject;

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest user) {
        try {
            responseObject = new LoginResponse(uService.login(user), 200);
            return ResponseEntity.ok().body(responseObject);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Login failed. Please check your credentials and try again.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
