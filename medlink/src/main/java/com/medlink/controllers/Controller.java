package com.medlink.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.medlink.models.ErrorResponse;
import com.medlink.models.HospitalModel;
import com.medlink.models.LoginRequest;
import com.medlink.models.UserModel;
import com.medlink.services.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class Controller {
    @Autowired
    UserService uService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest user) {
        try {
            return ResponseEntity.ok(this.uService.login(user));
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserModel user) {
        try {
            return ResponseEntity.ok(this.uService.signUp(user));
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/details")
    public String patientDetails(@RequestBody String entity) {
        // TODO: process POST request
        return entity;
    }

    @GetMapping("/hospitals/{location}")
    public ResponseEntity<?> getHospitals(@PathVariable String location) {
        try {
            return ResponseEntity.ok(this.uService.getHospitals(location));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PostMapping("/hospitals/upload")
    public ResponseEntity<?> postHospitals(@RequestBody List<HospitalModel>l) {
        try {
            return ResponseEntity.ok(this.uService.postHospitals(l));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/appointment")
    public String postAppointment(@RequestBody String entity) {
        // TODO: process POST request

        return entity;
    }

    @GetMapping("/appointment")
    public String getAppointment(@RequestBody String token) {
        // TODO: process GET request

        return token;
    }

}
