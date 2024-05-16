package com.medlink.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.medlink.models.ContactModel;
import com.medlink.models.ErrorResponse;
import com.medlink.models.HospitalModel;
import com.medlink.models.LoginRequest;
import com.medlink.models.LoginResponse;
import com.medlink.models.PatientInfo;
import com.medlink.models.UserModel;
import com.medlink.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Controller {
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

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserModel user) {
        try {
            String response = uService.register(user);
            ErrorResponse successResponse = new ErrorResponse(200, response);
            return ResponseEntity.ok().body(successResponse);
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse(500, "Registration failed. Please try again later.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/verify-account")
    public ResponseEntity<?> verifyAccount(@RequestParam String email,
            @RequestParam String otp) {
        try {
            String token = uService.verifyAccount(email, otp);
            if (token == null) {
                throw new Exception("Incorrect OTP. Please try again.");
            }
            LoginResponse responseModel = new LoginResponse(token, 200);
            return ResponseEntity.ok().body(responseModel);
        } catch (Exception ex) {
            String response = ex.getMessage();
            ErrorResponse errorResponse = new ErrorResponse(500, response);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // Get hospitals by location endpoint
    @GetMapping("/hospitals/{location}")
    public ResponseEntity<?> getHospitals(@PathVariable String location) {
        try {
            return ResponseEntity.ok(uService.getHospitals(location));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch hospitals. Please try again later.");
        }
    }

    // Upload hospitals endpoint
    @PostMapping("/hospitals/upload")
    public ResponseEntity<?> postHospitals(@RequestBody List<HospitalModel> hospitalList) {
        try {
            return ResponseEntity.ok(uService.postHospitals(hospitalList));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload hospitals. Please try again later.");
        }
    }

    // Post appointment endpoint
    @PostMapping("/appointment")
    public ResponseEntity<?> postAppointment(@RequestBody PatientInfo patientInfo) {
        try {
            PatientInfo savedPatientInfo = uService.postPatientInfo(patientInfo);
            return ResponseEntity.ok(savedPatientInfo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create appointment. Please try again later.");
        }
    }

    // Get appointment by ID endpoint
    @GetMapping("/appointment/{id}")
    public ResponseEntity<?> getAppointment(@PathVariable long id) {
        try {
            List<PatientInfo> patientInfoList = uService.getPatientInfo(id);
            return ResponseEntity.ok(patientInfoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch appointment information. Please try again later.");
        }
    }

    // Contact endpoint
    @PostMapping("/contact")
    public ResponseEntity<?> contact(@RequestBody ContactModel contactModel) {
        try {
            ContactModel savedContact = uService.getContact(contactModel);
            return ResponseEntity.ok(savedContact);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to submit contact information. Please try again later.");
        }
    }
}
