package com.medlink.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.medlink.models.HospitalModel;
import com.medlink.models.JwtRequest;
import com.medlink.models.LoginRequest;
import com.medlink.models.PatientInfo;
import com.medlink.models.UserModel;
import com.medlink.repository.HospitalRepository;
import com.medlink.repository.PatientInfoRepository;
import com.medlink.repository.UserRepository;
import com.medlink.utils.JwtUtils;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    HospitalRepository hRepository;

    @Autowired
    JwtUtils jwt;

    @Autowired
    PatientInfoRepository pRepository;

    public boolean authenticate(String token, JwtRequest user) {
        return this.jwt.validateToken(token, user);
    }

    public String createToken(JwtRequest user) {
        return this.jwt.generateToken(user);
    }

    public String login(LoginRequest log) throws Exception {
        try {
            UserModel user = userRepository.findByEmail(log.getEmail());
            if (user == null) {
                throw new Exception("User not found");
            }
            // Check if the provided password matches the stored password
            if (!user.getPassword().equals(log.getPassword())) {
                throw new Exception("Wrong password");
            }
            // If the email and password match, create and return a JWT token
            JwtRequest jwtRequest = new JwtRequest(log.getEmail(), user.getId());
            return createToken(jwtRequest);
        } catch (Exception e) {
            // e.printStackTrace();
            throw new Exception("Login failed: "+ e.getMessage());
        }
    }

    public String signUp(UserModel user) throws Exception {
        try {
            if(userRepository.findByEmail(user.getEmail())!=null){
                throw new Exception("User Already Exists");
            }
            UserModel s = userRepository.save(user);
            JwtRequest u = new JwtRequest(s.getEmail(), s.getId());
            return createToken(u);
        } catch (Exception e) {
            throw new Exception("Error SigningUp: "+e.getMessage());
        }
    }

    public List<HospitalModel> getHospitals(String location) throws Exception{
        try {
            return hRepository.findAllByLocation(location);
        } catch (Exception e) {
            throw new Exception("Error finding hospitals in the given location");
        }
    }
    public List<HospitalModel> postHospitals(List<HospitalModel>l) throws Exception{
        try {
            return hRepository.saveAll(l);
        } catch (Exception e) {
            throw new Exception("Error saving the hospitals");
        }
    }

    public PatientInfo postPatientInfo(PatientInfo p){
        return this.pRepository.save(p);
    }
    public List<PatientInfo> getPatientInfo(long id){
        return this.pRepository.findAllByPatientId(id);
    }

}
