package com.medlink.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medlink.models.HospitalModel;
import com.medlink.services.UserService;

@RestController
public class utilityController {
    @Autowired
    private UserService uService;

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
}
