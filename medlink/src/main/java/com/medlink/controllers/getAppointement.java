package com.medlink.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.medlink.models.PatientInfo;
import com.medlink.services.UserService;
@RestController
public class getAppointement {
    @Autowired
    private UserService uService;

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
}
