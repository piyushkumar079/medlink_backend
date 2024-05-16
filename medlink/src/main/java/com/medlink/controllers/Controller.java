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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class Controller {
    @Autowired
    private UserService userService;
    private LoginResponse responseObject;

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest user) {
        try {
            responseObject = new LoginResponse(userService.login(user));
            return ResponseEntity.ok().body(responseObject);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    
    // Signup endpoint
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserModel user) {
        try {
            responseObject = new LoginResponse(userService.signUp(user));
            return ResponseEntity.ok(responseObject);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // Get hospitals by location endpoint
    @GetMapping("/hospitals/{location}")
    public ResponseEntity<?> getHospitals(@PathVariable String location) {
        try {
            return ResponseEntity.ok(userService.getHospitals(location));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // Upload hospitals endpoint
    @PostMapping("/hospitals/upload")
    public ResponseEntity<?> postHospitals(@RequestBody List<HospitalModel> hospitalList) {
        try {
            return ResponseEntity.ok(userService.postHospitals(hospitalList));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // Post appointment endpoint
    @PostMapping("/appointment")
    public PatientInfo postAppointment(@RequestBody PatientInfo patientInfo) {
        return userService.postPatientInfo(patientInfo);
    }

    // Get appointment by ID endpoint
    @GetMapping("/appointment/{id}")
    public List<PatientInfo> getAppointment(@PathVariable long id) {
        return userService.getPatientInfo(id);
    }

    // Contact endpoint
    @PostMapping("/contact")
    public ContactModel contact(@RequestBody ContactModel contactModel) throws Exception {
        return userService.getContact(contactModel);
    }
}
