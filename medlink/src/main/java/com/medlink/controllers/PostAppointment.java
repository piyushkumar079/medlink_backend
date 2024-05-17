package com.medlink.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medlink.models.PatientInfo;
import com.medlink.services.UserService;
@RestController
public class PostAppointment {
    @Autowired
    private UserService uService;
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

}
