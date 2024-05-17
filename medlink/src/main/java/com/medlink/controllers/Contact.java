package com.medlink.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medlink.models.ContactModel;
import com.medlink.services.UserService;

@RestController
public class Contact {
    @Autowired
    private UserService uService; 
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
