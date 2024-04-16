package com.medlink.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class HospitalModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    Long id; 

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    String name;

    @NotBlank(message = "Address is required")
    @Column(nullable = false)
    String address;

    @NotBlank(message = "Location is required")
    @Column(nullable = false)
    String location;

    @NotBlank(message = "Contact info is required")
    @Column(nullable = false)
    String contactInfo;

    @NotBlank(message = "beds info is required")
    int beds;
}
