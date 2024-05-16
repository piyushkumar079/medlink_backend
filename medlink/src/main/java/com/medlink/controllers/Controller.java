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
import com.medlink.config.CorsConfig;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Controller {
    @Autowired
    UserService uService;
    LoginResponse l;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest user) {
        try {
            l = new LoginResponse(this.uService.login(user));
            return ResponseEntity.ok().body(l);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody UserModel user) {
    return new ResponseEntity<>(uService.register(user), HttpStatus.OK);
  }

  @PutMapping("/verify-account")
  public ResponseEntity<String> verifyAccount(@RequestParam String email,
      @RequestParam String otp) {
    return new ResponseEntity<>(uService.verifyAccount(email, otp), HttpStatus.OK);
  }

  @PutMapping("/regenerate-otp")
  public ResponseEntity<String> regenerateOtp(@RequestParam String email) {
    return new ResponseEntity<>(uService.regenerateOtp(email), HttpStatus.OK);
  }

    // @PostMapping("/signup")
    // public ResponseEntity<?> signUp(@RequestBody UserModel user) {
    //     try {
    //         l = new LoginResponse(this.uService.signUp(user));
    //         return ResponseEntity.ok(l);
    //     } catch (Exception e) {
    //         ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    //     }
    // }

    @GetMapping("/hospitals/{location}")
    public ResponseEntity<?> getHospitals(@PathVariable String location) {
        try {
            return ResponseEntity.ok(this.uService.getHospitals(location));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/hospitals/upload")
    public ResponseEntity<?> postHospitals(@RequestBody List<HospitalModel> l) {
        try {
            return ResponseEntity.ok(this.uService.postHospitals(l));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/appointment")
    public PatientInfo postAppointment(@RequestBody PatientInfo p) {
        return this.uService.postPatientInfo(p);
    }

    @GetMapping("/appointment/{id}")
    public List<PatientInfo> getAppointment(@PathVariable long id) {
        return this.uService.getPatientInfo(id);
    }

    @PostMapping("/contact")
    public ContactModel contact(@RequestBody ContactModel contactuser) throws Exception{
    return this.uService.getContact(contactuser);
    }
}
